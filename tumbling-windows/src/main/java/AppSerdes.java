import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import types.Invoice;

import java.util.HashMap;

public class AppSerdes extends Serdes {
    public static Serde<Invoice> Invoice(){
        var serde = new SpecificAvroSerde<Invoice>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }
}
