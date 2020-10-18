package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Producer {

    public static void main(String[] args){
        var props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "kafka-message-producer");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "kafka-message-producer-transaction-id");

        var producer = new KafkaProducer<Integer, String>(props);
        producer.initTransactions();

        List<ProducerRecord<Integer, String>> batch  = new ArrayList<ProducerRecord<Integer, String>>(1000);
        for (var i = 0; i< 100_000; i++){
            var rec = new ProducerRecord<>("simple-topic", i, "Simple-message-"+i);
            batch.add(rec);
            var capacity = batch.size();
            if(capacity % 1_000 == 0){
                System.out.println("sending messages: "+i);
                sendRecords(producer, batch);
                batch.clear();
            }
        }
        System.out.println("sending messages completed");

        producer.close();
    }

    private static void sendRecords(KafkaProducer<Integer, String> producer, List<ProducerRecord<Integer, String>> batch) {
        try {
            producer.beginTransaction();
            for (var r : batch) {
                producer.send(r);
            }
            producer.commitTransaction();
        }
        catch (Exception ex){
            ex.printStackTrace();
            producer.abortTransaction();
        }
    }
}
