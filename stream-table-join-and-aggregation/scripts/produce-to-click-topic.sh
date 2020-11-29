export CONFLUENT_HOME=~/tools/kafka/confluent-6.0.0
export PATH=$PATH:$CONFLUENT_HOME/bin
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
kafka-avro-console-producer --broker-list localhost:9092 --topic click \
--property parse.key=true \
--property key.separator=: \
--property key.schema='{"type":"string"}' \
--property value.schema='{"type":"record","name":"Click","namespace":"types","fields":[{"name":"InventoryID","type":"string"}]}'