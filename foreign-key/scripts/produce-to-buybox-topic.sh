export CONFLUENT_HOME=~/tools/kafka/confluent-6.0.0
export PATH=$PATH:$CONFLUENT_HOME/bin
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
kafka-console-producer --broker-list localhost:19092 --topic buybox \
--property parse.key=true \
--property key.separator=: