import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import types.Invoice;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Properties;

public class StreamApp {
    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);

        var builder = new StreamsBuilder();
        KStream<String, Invoice> invStream = builder.stream(AppConfigs.posTopicName,
            Consumed.with(AppSerdes.String(), AppSerdes.Invoice())
                .withTimestampExtractor(new InvoiceTimeExtractor()));

        KTable<Windowed<String>, Long> storeInvoiceCount = invStream
            .groupByKey()
            .windowedBy(TimeWindows.of(Duration.ofMinutes(5)).grace(Duration.ofSeconds(10)))
            .count();
//            .suppress(Suppressed.untilTimeLimit(Duration.ofSeconds(10), Suppressed.BufferConfig.maxBytes(100000).emitEarlyWhenFull()));

        storeInvoiceCount.toStream().foreach((wKey, value) -> {
            System.out.println( "Store ID: " + wKey.key() + " Window ID: " + wKey.window().hashCode() +
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
