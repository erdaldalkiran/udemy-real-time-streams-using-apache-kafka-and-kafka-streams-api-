package streamer;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.Stores;
import types.Notification;
import types.PosInvoice;

import java.util.Properties;

public class StreamApp {
    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);

        var builder = new StreamsBuilder();

        var rewardStoreBuilder = Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore(AppConfigs.rewardStoreName),
                AppSerdes.String(),
                AppSerdes.Double());
        builder.addStateStore(rewardStoreBuilder);

        builder.stream(AppConfigs.posTopicName, Consumed.with(AppSerdes.String(), AppSerdes.PosInvoice()))
                .filter((s, invoice) -> invoice.getCustomerType().toString().equalsIgnoreCase(AppConfigs.CUSTOMER_TYPE_PRIME))
                .transformValues(InvoiceToNotificationTransformer::new, AppConfigs.rewardStoreName)
                .selectKey((s, notification) -> notification.getCustomerCardNo().toString())
                .to(AppConfigs.loyaltyTopicName, Produced.with(AppSerdes.String(), AppSerdes.Notification()));

        var stream = new KafkaStreams(builder.build(), props);
        stream.start();

        Runtime.getRuntime().addShutdownHook(new Thread(stream::close));
    }
}

class InvoiceToNotificationTransformer implements ValueTransformer<PosInvoice, Notification> {
    private KeyValueStore<String, Double> store;

    @Override
    public void init(ProcessorContext processorContext) {
        this.store = (KeyValueStore<String, Double>) processorContext.getStateStore(AppConfigs.rewardStoreName);
    }

    @Override
    public Notification transform(PosInvoice posInvoice) {
        var totalRewardsSoFar = store.get(posInvoice.getCustomerCardNo().toString());
        if (totalRewardsSoFar == null) {
            totalRewardsSoFar = (double) 0;
        }
        var earnedLoyalty = posInvoice.getTotalAmount() * AppConfigs.LOYALTY_FACTOR;
        var totalRewards = totalRewardsSoFar + earnedLoyalty;

        store.put(posInvoice.getCustomerCardNo().toString(), totalRewards);

        return Notification.newBuilder()
                .setCustomerCardNo(posInvoice.getCustomerCardNo())
                .setInvoiceNumber(posInvoice.getInvoiceNumber())
                .setTotalAmount(posInvoice.getTotalAmount())
                .setEarnedLoyaltyPoints(earnedLoyalty)
                .setTotalLoyaltyPoints(totalRewards)
                .build();
    }

    @Override
    public void close() {

    }
}
