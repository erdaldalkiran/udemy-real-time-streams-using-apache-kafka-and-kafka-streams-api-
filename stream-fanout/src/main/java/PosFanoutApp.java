import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import serde.AppSerdes;
import types.PosInvoice;

import java.util.Properties;

public class PosFanoutApp {
    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);

        var sBuilder = new StreamsBuilder();
        KStream<String, PosInvoice> invoicesStream = sBuilder.stream(
            AppConfigs.posTopicName,
            Consumed.with(AppSerdes.String(), AppSerdes.PosInvoice()));

        //send home deliveries to shipment
        invoicesStream
            .filter((k, invoice) -> invoice.getDeliveryType().equalsIgnoreCase(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY))
            .to(AppConfigs.shipmentTopicName,
                Produced.with(AppSerdes.String(), AppSerdes.PosInvoice()));

        //send prime customers to loyalty service
        invoicesStream
            .filter((k, invoice) -> invoice.getCustomerType().equalsIgnoreCase(AppConfigs.CUSTOMER_TYPE_PRIME))
            .mapValues(RecordBuilder::getNotification)
            .to(AppConfigs.notificationTopic,
                Produced.with(AppSerdes.String(), AppSerdes.Notification()));

        //send invoices to hadoop after masking them
        invoicesStream
            .mapValues(RecordBuilder::getMaskedInvoice)
            .flatMapValues(RecordBuilder::getHadoopRecords)
            .to(AppConfigs.hadoopTopic,
                Produced.with(AppSerdes.String(), AppSerdes.HadoopRecord()));

        var topology = sBuilder.build();

        var streams = new KafkaStreams(topology,props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
