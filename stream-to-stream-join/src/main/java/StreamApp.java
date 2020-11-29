import configs.AppConfigs;
import configs.TimestampExtractors;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.StreamJoined;
import serdes.AppSerdes;
import types.TransactionStatus;

import java.time.Duration;
import java.util.Properties;

public class StreamApp {
    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        var builder = new StreamsBuilder();

        var requestStream = builder.stream(
                AppConfigs.paymentRequestTopic,
                Consumed.with(AppSerdes.String(), AppSerdes.PaymentRequest())
                        .withTimestampExtractor(TimestampExtractors.PaymentRequest()));

        var confirmationStream = builder.stream(
                AppConfigs.paymentConfirmationTopic,
                Consumed.with(AppSerdes.String(), AppSerdes.PaymentConfirmation())
                        .withTimestampExtractor(TimestampExtractors.PaymentConfirmation()));

        requestStream.join(confirmationStream, (req, conf) -> {
                    var status = new TransactionStatus();
                    status.setTransactionID(req.getTransactionID());
                    status.setStatus(req.getOTP() == conf.getOTP() ? "Success" : "Failure");
                    return status;
                },
                JoinWindows.of(Duration.ofMinutes(5)),
                StreamJoined.with(AppSerdes.String(), AppSerdes.PaymentRequest(), AppSerdes.PaymentConfirmation())
        ).print(Printed.toSysOut());

        var streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            streams.close();
        }));

    }
}
