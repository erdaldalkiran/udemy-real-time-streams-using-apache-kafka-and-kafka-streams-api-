import configs.AppConfigs;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;
import serdes.AppSerdes;
import types.CampaignPerformance;

public class AppTopology {
    public static void withBuilder(StreamsBuilder builder) {
        var impressionCountStream = builder.stream(
            AppConfigs.impressionTopic,
            Consumed.with(AppSerdes.String(), AppSerdes.Impression()))
            .groupBy((k, v) -> v.getCampaigner().toString(), Grouped.with(AppSerdes.String(), AppSerdes.Impression()))
            .count();

        var clickCountStream = builder.stream(
            AppConfigs.clickTopic,
            Consumed.with(AppSerdes.String(), AppSerdes.Click()))
            .groupBy((k, v) -> v.getCampaigner().toString(), Grouped.with(AppSerdes.String(), AppSerdes.Click()))
            .count();

        var campaignPerformances = impressionCountStream.leftJoin(
            clickCountStream,
            (impression, click) -> {
                var p = new CampaignPerformance();
                p.setClickCount(click == null ? 0 : click);
                p.setImpressionCount(impression);
                return p;
            })
            .mapValues((k, v) -> {
                    v.setCampaigner(k);
                    return v;
                },
                Materialized
                    .<String, CampaignPerformance, KeyValueStore<Bytes, byte[]>>as(AppConfigs.campaignPerformanceStateStore)
                    .withKeySerde(AppSerdes.String())
                    .withValueSerde(AppSerdes.CampaignPerformance())
            );

        campaignPerformances.toStream().to(AppConfigs.outputTopic, Produced.with(AppSerdes.String(), AppSerdes.CampaignPerformance()));
    }
}
