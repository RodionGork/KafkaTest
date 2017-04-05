# KafkaTest

Just a simple test for kafka API.

1. bin/zookeeper-server-start.sh config/zookeeper.properties
2. bin/kafka-server-start.sh config/server.properties
3. bin/kafka-topic.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic zloba
4. mvn package
5. java -jar target/kafka-fib.jar consume 10
6. java -jar target/kafka-fib.jar produce 1000