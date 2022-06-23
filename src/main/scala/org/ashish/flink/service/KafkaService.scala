package org.ashish.flink.service

import org.ashish.flink.model.Car
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.json4s.{DefaultFormats, _}
import org.json4s.native.JsonMethods
import org.apache.flink.streaming.api.scala._

import java.util.Properties

class KafkaService {

  def kafkaStreamConsumer(environment: StreamExecutionEnvironment):DataStream[Car] ={
    implicit lazy val formats: DefaultFormats.type = org.json4s.DefaultFormats
    val properties: Properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092");
    properties.setProperty("group.id", "KafkaCassandra")

    val kafkaConsumer = new FlinkKafkaConsumer[String]("car.create", new SimpleStringSchema(), properties)
    environment.addSource(kafkaConsumer).flatMap(raw => JsonMethods.parse(raw).toOption)
      .map(_.extract[Car])
  }

}
