package stream;

public class AppConfigs {
    public final static String applicationID = "pos-invoice-streamer";
    public final static String bootstrapServers = "localhost:9092, localhost:9093";
    public final static String posTopicName = "pos";
    public final static String schemaRegistryUrl = "http://localhost:8081";
    public final static String DELIVERY_TYPE_HOME_DELIVERY = "HOME-DELIVERY";
    public final static String CUSTOMER_TYPE_PRIME = "PRIME";
    public final static String shipmentTopicName = "shipment";
    public final static String hadoopTopicName = "hadoop-sink";
    public final static String loyaltyTopicName = "loyalty";
    public final static Double LOYALTY_FACTOR = 0.02;
}
