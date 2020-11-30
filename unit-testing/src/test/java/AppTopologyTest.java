import configs.AppConfigs;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.junit.jupiter.api.*;
import serdes.AppSerdes;
import types.CampaignPerformance;
import types.Click;
import types.Impression;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppTopologyTest {

    private static TopologyTestDriver topologyTestDriver;
    private static TestInputTopic<String, Impression> impressionTopic;
    private static TestInputTopic<String, Click> clickTopic;
    private static TestOutputTopic<String, CampaignPerformance> outputTopic;
    private static KeyValueStore<String, CampaignPerformance> store;

    @BeforeAll
    static void beforeAll() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, "tmp/kafka-test/state-store");

        var streamsBuilder = new StreamsBuilder();
        AppTopology.withBuilder(streamsBuilder);
        var topology = streamsBuilder.build();

        topologyTestDriver = new TopologyTestDriver(topology, props);

        impressionTopic = topologyTestDriver.createInputTopic(
            AppConfigs.impressionTopic,
            AppSerdes.String().serializer(),
            AppSerdes.Impression().serializer());

        clickTopic = topologyTestDriver.createInputTopic(
            AppConfigs.clickTopic,
            AppSerdes.String().serializer(),
            AppSerdes.Click().serializer());

        outputTopic = topologyTestDriver.createOutputTopic(
            AppConfigs.outputTopic,
            AppSerdes.String().deserializer(),
            AppSerdes.CampaignPerformance().deserializer());

        store = topologyTestDriver.getKeyValueStore(AppConfigs.campaignPerformanceStateStore);
    }

    @AfterAll
    static void afterAll() {
        topologyTestDriver.close();
    }

    @Test
    @Order(1)
    @DisplayName("Test the impression flow from the source topic to the final output topic.")
    void impressionFlowTest() {
        var impression = new Impression("100001", "ABC Ltd");
        impressionTopic.pipeInput(impression);

        var cp = outputTopic.readValue();

        assertAll(
            () -> assertEquals("ABC Ltd", cp.getCampaigner().toString()),
            () -> assertEquals(1, cp.getImpressionCount()),
            () -> assertEquals(0, cp.getClickCount())
        );
    }

    @Test
    @Order(2)
    @DisplayName("Test the impression count increments correctly.")
    void impressionCountIncreased() {
        var impression = new Impression("100001", "ABC Ltd");
        impressionTopic.pipeInput(impression);

        var outputTopic = topologyTestDriver.createOutputTopic(
            AppConfigs.outputTopic,
            AppSerdes.String().deserializer(),
            AppSerdes.CampaignPerformance().deserializer());


        var cp = outputTopic.readValue();

        assertAll(
            () -> assertEquals("ABC Ltd", cp.getCampaigner().toString()),
            () -> assertEquals(2, cp.getImpressionCount()),
            () -> assertEquals(0, cp.getClickCount())
        );
    }

    @Test
    @Order(3)
    @DisplayName("Test the click count increments correctly.")
    void clickCountIncreased() {
        var click = new Click("100001", "ABC Ltd");
        clickTopic.pipeInput(click);

        var outputTopic = topologyTestDriver.createOutputTopic(
            AppConfigs.outputTopic,
            AppSerdes.String().deserializer(),
            AppSerdes.CampaignPerformance().deserializer());


        var cp = outputTopic.readValue();

        assertAll(
            () -> assertEquals("ABC Ltd", cp.getCampaigner().toString()),
            () -> assertEquals(2, cp.getImpressionCount()),
            () -> assertEquals(1, cp.getClickCount())
        );
    }

    @Test
    @Order(4)
    @DisplayName("Test the state store holds the correct state")
    void stateStoreTest() {
        CampaignPerformance cpValue = store.get("ABC Ltd");

        assertAll(
            () -> assertEquals("ABC Ltd", cpValue.getCampaigner().toString()),
            () -> assertEquals(2, cpValue.getImpressionCount()),
            () -> assertEquals(1, cpValue.getClickCount())
        );

    }
}