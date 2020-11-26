import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import types.Invoice;

import java.time.Instant;

public class InvoiceTimeExtractor implements TimestampExtractor {
    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        var inv = (Invoice)consumerRecord.value();
        var ep = Instant.parse(inv.getCreatedTime()).toEpochMilli();
        return ep > 0 ? ep : prevTime;
    }
}
