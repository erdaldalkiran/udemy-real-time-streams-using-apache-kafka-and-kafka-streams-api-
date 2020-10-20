package streamer;

public  class AppConfigs {
    public final static String applicationID="customer-reward-calculator-1";
    public final static String bootstrapServers="localhost:9092, localhost:9093";
    public final static String schemaRegistryUrl="http://localhost:8081";
    public final static String posTopicName="pos";
    public final static String loyaltyTopicName = "loyalty";
    public final static String rewardStoreName = "reward-store";
    public final static String CUSTOMER_TYPE_PRIME = "PRIME";
    public final static Double LOYALTY_FACTOR = 0.02;
    public final static String rewardCalculationTopicName = "reward-calculation";
}
