export CONFLUENT_HOME=~/tools/kafka/confluent-6.0.0
export PATH=$PATH:$CONFLUENT_HOME/bin
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
kafka-avro-console-producer --broker-list localhost:9092 --topic payment-request \
--property parse.key=true \
--property key.separator=: \
--property key.schema='{"type":"string"}' \
--property value.schema='{"type":"record","name":"PaymentRequest","namespace":"types","fields":[{"name":"TransactionID","type":"string"},{"name":"CreatedTime","type":"string"},{"name":"SourceAccountID","type":"string"},{"name":"TargetAccountID","type":"string"},{"name":"Amount","type":"double"},{"name":"OTP","type":"int"}]}'