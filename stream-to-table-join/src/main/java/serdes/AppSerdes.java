package serdes;

import configs.AppConfigs;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import types.Click;
import types.Inventory;

import java.util.HashMap;

public class AppSerdes extends Serdes {
    public static Serde<Click> Click() {
        var serde = new SpecificAvroSerde<Click>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }

    public static Serde<Inventory> Inventory() {
        var serde = new SpecificAvroSerde<Inventory>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }
}


