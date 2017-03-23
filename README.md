# kafka-meetup-producer
A simple kafka producer based on Meetup open data

## Steps

  ### Installation
  ```
  git clone https://github.com/Wconti/kafka-meetup-producer
  cd path/to/your/kafka/installation
  bin/kafka-topics --create \
          --zookeeper localhost:2181 \
          --replication-factor 1 \
          --partitions 1 \
          --topic test
  ```

  ### Run
  `java -cp target/meetup-producer-1.0-SNAPSHOT.jar com.wconti.app.Producer`

  ### Monitoring output on kafka
  
  ```
  cd path/to/your/kafka/installation
  bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test
  ```
