package configs;

public class AppConfigs {
    public final static String applicationID = "stream-to-table-join-and-aggregation-3";
    public final static String bootstrapServers = "localhost:9092, localhost:9093";
    public final static String clickTopic = "click";
    public final static String inventoryTopic = "inventory";
    public final static String inventoryClickGroupKey = "icgk";
    public final static int topNLimit = 3;
    public final static String schemaRegistryUrl = "http://localhost:8081";
}
