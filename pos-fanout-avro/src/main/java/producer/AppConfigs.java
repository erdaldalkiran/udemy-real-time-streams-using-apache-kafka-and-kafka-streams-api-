package producer;

public  class AppConfigs {
    public final static String applicationID="pos-invoice-producer";
    public final static String bootstrapServers="localhost:9092, localhost:9093";
    public final static String topic="pos";
    public final static String transactionID="pos-invoice-producer-transaction-id";
    public final static String schemaRegistryUrl="http://localhost:8081";
}
