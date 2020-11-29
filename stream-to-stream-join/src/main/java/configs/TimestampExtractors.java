package configs;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import types.PaymentConfirmation;
import types.PaymentRequest;

import java.time.Instant;

public class TimestampExtractors {

    private static final class PaymentRequestTimestampExtractor implements TimestampExtractor {
        @Override
        public long extract(ConsumerRecord<Object, Object> consumerRecord, long previousTime) {
            var req = (PaymentRequest) consumerRecord.value();
            var time = Instant.parse(req.getCreatedTime()).toEpochMilli();
            return time > 0 ? time : previousTime;
        }
    }

    public static PaymentRequestTimestampExtractor PaymentRequest() {
        return new PaymentRequestTimestampExtractor();
    }

    private static final class PaymentConfirmationTimestampExtractor implements TimestampExtractor {
        @Override
        public long extract(ConsumerRecord<Object, Object> consumerRecord, long previousTime) {
            var req = (PaymentConfirmation) consumerRecord.value();
            var time = Instant.parse(req.getCreatedTime()).toEpochMilli();
            return time > 0 ? time : previousTime;
        }
    }

    public static PaymentConfirmationTimestampExtractor PaymentConfirmation() {
        return new PaymentConfirmationTimestampExtractor();
    }
}
