package stream;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import types.PosInvoice;

import java.util.Properties;

public class StreamApp {
    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);

        var builder = new StreamsBuilder();
        KStream<String, PosInvoice> posStream = builder.stream(AppConfigs.posTopicName, Consumed.with(AppSerdes.String(), AppSerdes.PosInvoice()));

        //to shipment
        posStream
                .filter((key, inv) -> inv.getDeliveryType().toString().equals(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY))
                .to(AppConfigs.shipmentTopicName, Produced.with(AppSerdes.String(), AppSerdes.PosInvoice()));

        //to loyalty
        posStream
                .filter((key, inv) -> inv.getCustomerType().toString().equals(AppConfigs.CUSTOMER_TYPE_PRIME))
                .mapValues((key, inv) -> RecordBuilder.getNotification(inv))
                .to(AppConfigs.loyaltyTopicName, Produced.with(AppSerdes.String(), AppSerdes.Notification()));

        //to hadoop
        posStream
                .mapValues((key, inv) -> RecordBuilder.getMaskedInvoice(inv))
                .flatMapValues((key, inv) -> RecordBuilder.getHadoopRecords(inv))
                .to(AppConfigs.hadoopTopicName, Produced.with(AppSerdes.String(), AppSerdes.HadoopRecord()));

        var topology = builder.build();

        var streams = new KafkaStreams(topology, props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
