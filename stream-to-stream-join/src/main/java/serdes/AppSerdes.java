package serdes;

import configs.AppConfigs;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import types.PaymentConfirmation;
import types.PaymentRequest;
import types.TransactionStatus;

import java.util.HashMap;

public class AppSerdes extends Serdes {
    public static Serde<PaymentRequest> PaymentRequest() {
        var serde = new SpecificAvroSerde<PaymentRequest>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }

    public static Serde<PaymentConfirmation> PaymentConfirmation() {
        var serde = new SpecificAvroSerde<PaymentConfirmation>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }

    public static Serde<TransactionStatus> TransactionStatus() {
        var serde = new SpecificAvroSerde<TransactionStatus>();

        var props = new HashMap<String, Object>();
        props.put("schema.registry.url", AppConfigs.schemaRegistryUrl);
        serde.configure(props, false);

        return serde;
    }
}


