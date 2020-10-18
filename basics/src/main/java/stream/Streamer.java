package stream;

import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class Streamer {
    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streamer");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        var sBuilder = new StreamsBuilder();
        KStream<Integer, String> kStream = sBuilder.stream("simple-topic");
        kStream.foreach((k, v) -> System.out.println("key: " + k + " value: " + v));

        var topology = sBuilder.build();
        var streams = new KafkaStreams(topology,props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

}
