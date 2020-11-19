package streamer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;

import java.util.Properties;

public class StreamApp {
    public static void main(String[] args){
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Double().getClass());

        var builder = new StreamsBuilder();
        KTable<String, Double> stockTicksTable = builder.table(AppConfigs.stockTickTopicName);
        stockTicksTable.toStream().print(Printed.<String, Double>toSysOut().withLabel("stock-ticks"));

        var filteredStockTicks = stockTicksTable.filter(
                (key, price) ->  (key.equalsIgnoreCase("hede") || key.equalsIgnoreCase("ciko")) /*&& price != null*/,
                Materialized.as(AppConfigs.filteredStockTickTopicName));

        filteredStockTicks.toStream().print(Printed.<String, Double>toSysOut().withLabel("filtered-stock-ticks"));

        var stream = new KafkaStreams(builder.build(), props);
        stream.start();

        Runtime.getRuntime().addShutdownHook(new Thread(stream::close));
    }
}
