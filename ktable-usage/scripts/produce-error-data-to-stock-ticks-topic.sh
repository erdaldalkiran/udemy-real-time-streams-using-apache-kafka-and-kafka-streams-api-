export CONFLUENT_HOME=~/tools/kafka/confluent-6.0.0
export PATH=$PATH:$CONFLUENT_HOME/bin
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
kafka-console-producer --topic stock-ticks --broker-list localhost:9092 --property parse.key=true --property key.separator=:
