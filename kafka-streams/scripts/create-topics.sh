export CONFLUENT_HOME=~/tools/kafka/confluent-6.0.0
export PATH=$PATH:$CONFLUENT_HOME/bin
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
kafka-topics --create --zookeeper localhost:2181 --topic buybox --partitions 1 --replication-factor 3 --config min.insync.replicas=3
kafka-topics --create --zookeeper localhost:2181 --topic enhanced-listing --partitions 1 --replication-factor 3 --config min.insync.replicas=3
kafka-topics --create --zookeeper localhost:2181 --topic listing --partitions 1 --replication-factor 3 --config min.insync.replicas=3
kafka-topics --create --zookeeper localhost:2181 --topic product --partitions 1 --replication-factor 3 --config min.insync.replicas=3