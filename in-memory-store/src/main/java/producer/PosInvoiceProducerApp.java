package producer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import producer.datagenerator.InvoiceGenerator;
import types.PosInvoice;

import java.util.ArrayList;
import java.util.Properties;

public class PosInvoiceProducerApp {
    public static void main(String[] args) throws InterruptedException {
        var props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, AppConfigs.transactionID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, AppConfigs.schemaRegistryUrl);

        var producer = new KafkaProducer<String, PosInvoice>(props);
        producer.initTransactions();

        var generator = InvoiceGenerator.getInstance();
        var batch = new ArrayList<PosInvoice>(100);
        while (true){
            batch.add(generator.getNextInvoice());
            if(batch.size() % 100 == 0){
                sendRecords(producer, batch);
                batch.clear();
                Thread.sleep(1000);
            }
        }
    }

    private static void sendRecords(KafkaProducer<String, PosInvoice> producer, ArrayList<PosInvoice> batch) {
        try{
            producer.beginTransaction();
            for (var invoice : batch) {
                producer.send(new ProducerRecord<>(AppConfigs.topic, invoice.getInvoiceNumber().toString(), invoice));
            }
            producer.commitTransaction();
        }
        catch (Exception ex){
            producer.abortTransaction();
            System.out.println("error while sending records.");
            ex.printStackTrace();
        }
    }
}
