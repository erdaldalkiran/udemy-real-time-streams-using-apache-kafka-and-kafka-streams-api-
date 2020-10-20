package streamer;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import types.Notification;
import types.PosInvoice;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {

    public static Serde<PosInvoice> PosInvoice(){
        var serde = new SpecificAvroSerde<PosInvoice>();
        Map<String, String> props = new HashMap<>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);
        return serde;
    }

    public static Serde<Notification> Notification(){
        var serde = new SpecificAvroSerde<Notification>();
        Map<String, String> props = new HashMap<>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);
        return serde;
    }
}
