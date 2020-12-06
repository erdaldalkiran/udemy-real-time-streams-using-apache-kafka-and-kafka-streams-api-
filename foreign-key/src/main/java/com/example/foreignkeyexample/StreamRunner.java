package com.example.foreignkeyexample;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class StreamRunner {

    @Value("${spring.cloud.stream.kafka.streams.binder.application-id}")
    private String applicationID;

    @Value("${spring.cloud.stream.kafka.streams.binder.brokers}")
    private String brokers;

    @Value("${spring.cloud.stream.kafka.streams.binder.deserialization-exception-handler}")
    private String deserializationExceptionHandler;

    @Value("${spring.cloud.stream.kafka.streams.binder.configuration.commit.interval.ms}")
    private int commitIntervalMS;

    @Value("${spring.cloud.stream.kafka.streams.binder.configuration.listing.topic.name}")
    private String listingTopicName;

    @Value("${spring.cloud.stream.kafka.streams.binder.configuration.buybox.topic.name}")
    private String buyboxTopicName;

    @Value("${spring.cloud.stream.kafka.streams.binder.configuration.product.topic.name}")
    private String productTopicName;

    @Value("${spring.cloud.stream.kafka.streams.binder.configuration.enhanced-listing.topic.name}")
    private String enhancedListingTopicName;

    public void Run() {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, commitIntervalMS);
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);

        var streamBuilder = new StreamsBuilder();
        var listingTable = streamBuilder.table(listingTopicName, Consumed.with(AppSerdes.UUID(), AppSerdes.Listing()));
        listingTable.toStream().print(Printed.toSysOut());

        var topology = streamBuilder.build();
        var stream = new KafkaStreams(topology, props);
        stream.start();

        Runtime.getRuntime().
            addShutdownHook(new Thread(() ->
            {
                stream.close();
            }));
    }
}
