import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import types.Click;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Properties;

public class StreamApp {
    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        var builder = new StreamsBuilder();
        KStream<String, Click> clickStream = builder.stream(AppConfigs.topicName,
                Consumed.with(AppSerdes.String(), AppSerdes.Click())
                        .withTimestampExtractor(new ClickTimeExtractor()));

        KTable<Windowed<String>, Long> clickCounts = clickStream
                .groupByKey()
                .windowedBy(SessionWindows.with(Duration.ofMinutes(5)))
                .count();

        clickCounts.toStream().foreach((wKey, value) -> {
            System.out.println("User ID: " + wKey.key() + " Window ID: " + wKey.window().hashCode() +
                    " Window start: " + Instant.ofEpochMilli(wKey.window().start()).atOffset(ZoneOffset.UTC) +
                    " Window end: " + Instant.ofEpochMilli(wKey.window().end()).atOffset(ZoneOffset.UTC) +
                    " Count: " + value);
        });

        var stream = new KafkaStreams(builder.build(), props);
        stream.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping Streams");
            stream.close();
        }));
    }
}
