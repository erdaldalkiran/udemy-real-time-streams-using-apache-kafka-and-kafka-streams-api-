package configs;

public class AppConfigs {
    public final static String applicationID = "stream-to-stream-join";
    public final static String bootstrapServers = "localhost:9092, localhost:9093";
    public final static String paymentRequestTopic = "payment-request";
    public final static String paymentConfirmationTopic = "payment-confirmation";
    public final static String schemaRegistryUrl = "http://localhost:8081";
}
