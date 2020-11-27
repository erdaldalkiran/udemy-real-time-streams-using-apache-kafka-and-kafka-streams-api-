import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import types.Click;

import java.time.Instant;

public class ClickTimeExtractor implements TimestampExtractor {
    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        var c = (Click) consumerRecord.value();
        var ep = Instant.parse(c.getCreatedTime()).toEpochMilli();
        return ep > 0 ? ep : prevTime;
    }
}
