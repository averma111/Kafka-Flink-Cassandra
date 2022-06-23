package org.ashish.flink.driver

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.ashish.flink.service.{CassandraService, KafkaService}

object StartJob {

  def main(args: Array[String]): Unit = {

    val environment = StreamExecutionEnvironment.getExecutionEnvironment

    //Instantiating KafkaService Class and Consume message from kafka topic.
    val carStream = new KafkaService().kafkaStreamConsumer(environment)

    //Do something with carDataStream

    //Instantiating CassandraService Class and sinking data into CassandraDB.
    val cassandraService = new CassandraService()
    cassandraService.sinkToCassandraDB(carStream)

    environment.execute("Flink Kafka to cassandra app")

  }
}