import configs.AppConfigs;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Printed;
import serdes.AppSerdes;

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

        clickStream.join(
                inventoryTable,
                (click, inventory) -> inventory)
                .groupBy((k, inv) -> inv.getNewsType().toString(), Grouped.with(AppSerdes.String(), AppSerdes.Inventory()))
                .count()
                .toStream().print(Printed.toSysOut());

        var streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            streams.close();
        }));
    }
}
