import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;

public class WordCountApp {
    public static void main(final String[] args){

        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        var builder = new StreamsBuilder();
        KStream<String, String> wordStream = builder.stream(AppConfigs.topicName);
        var groupedStream = wordStream
            .flatMapValues(v -> Arrays.asList(v.toLowerCase().split(" ")))
            .groupBy((k, v) -> v );
        var countTable = groupedStream.count();
        countTable.toStream().print(Printed.<String, Long>toSysOut().withLabel("WC:"));

        var streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}
