import configs.AppConfigs;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import serdes.AppSerdes;
import types.InventoryClick;
import types.InventoryClickList;
import types.InventoryClickListSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class StreamApp {
    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        var builder = new StreamsBuilder();

        var clickStream = builder.stream(
                AppConfigs.clickTopic,
                Consumed.with(AppSerdes.String(), AppSerdes.Click()));

        var inventoryTable = builder.table(
                AppConfigs.inventoryTopic,
                Consumed.with(AppSerdes.String(), AppSerdes.Inventory()));

        var inventoryClickCounts = clickStream.join(
                inventoryTable,
                (click, inventory) -> inventory)
                .groupBy((k, inv) -> inv.getNewsType().toString(), Grouped.with(AppSerdes.String(), AppSerdes.Inventory()))
                .count();

        var topNClicksByInventory = inventoryClickCounts.groupBy((inv, count) -> {
            var ic = new InventoryClick();
            ic.setInventoryID(inv);
            ic.setClickCount(count);
            return KeyValue.pair(AppConfigs.inventoryClickGroupKey, ic);
        }, Grouped.with(AppSerdes.String(), AppSerdes.InventoryClick()))
                .aggregate(
                        () -> new InventoryClickList(Collections.emptyList()),
                        (key, inventoryClick, inventoryClickList) -> InventoryClickListSorter.sort(inventoryClickList, inventoryClick, AppConfigs.topNLimit),
                        (s, inventoryClick, inventoryClickList) -> {
                            var items = new ArrayList<>(inventoryClickList.getItems());
                            items.remove(inventoryClick);
                            return new InventoryClickList(items);
                        },
                        Materialized.<String, InventoryClickList, KeyValueStore<Bytes, byte[]>>
                                as("top" + AppConfigs.topNLimit + "-clicks")
                                .withKeySerde(AppSerdes.String())
                                .withValueSerde(AppSerdes.InventoryClickList()));

        topNClicksByInventory.toStream().foreach((k, v) -> {
            try {
                System.out.println("k=" + k + " v= " + v.getItems());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        var streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().
                addShutdownHook(new Thread(() ->
                {
                    streams.close();
                }));
    }
}
