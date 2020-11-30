import configs.AppConfigs;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

public class StreamApp {
    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        var builder = new StreamsBuilder();

        AppTopology.withBuilder(builder);

        var streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime()
            .addShutdownHook(new Thread(streams::close));
    }
}
