package stream;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import types.HadoopRecord;
import types.Notification;
import types.PosInvoice;

import java.util.HashMap;

public class AppSerdes extends Serdes {
    public static Serde<PosInvoice> PosInvoice(){
        var serde = new SpecificAvroSerde<PosInvoice>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }

    public static Serde<Notification> Notification(){
        var serde = new SpecificAvroSerde<Notification>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }

    public static Serde<HadoopRecord> HadoopRecord(){
        var serde = new SpecificAvroSerde<HadoopRecord>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }
}
