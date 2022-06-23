package org.ashish.flink.service

import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.connectors.cassandra.CassandraSink
import org.ashish.flink.model.Car
import org.apache.flink.streaming.api.scala._

class CassandraService {

  def sinkToCassandraDB(sinkCarStream:DataStream[Car]) :Unit ={
    createTypeInformation[(String, Long, Long)]

    val sinkCarDataStream = sinkCarStream.map(car => (car.Name, car.Cylinders,
      car.Horsepower))
    CassandraSink.addSink(sinkCarDataStream)
      .setHost("127.0.0.1")
      .setQuery("INSERT INTO example.car(name, cylinders, horsepower) values (?, ?, ?);")
      .build

  }
}
