import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import types.Click;

import java.util.HashMap;

public class AppSerdes extends Serdes {
    public static Serde<Click> Click() {
        var serde = new SpecificAvroSerde<Click>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }
}
