name := "Kafka-Flink-Cassandra"

version := "0.1"

scalaVersion := "2.12.13"

mainClass in compile := Some("org.ashish.flink.driver.StartJob")

assemblyJarName in assembly := "kafka-flink-cassandra-sbt-assembly-fatjar-1.0.jar"

libraryDependencies ++= Seq(
  //kafka flink connector
  "org.apache.flink" %% "flink-connector-kafka" % "1.9.0",
  //flink scala streaming
  "org.apache.flink" %% "flink-streaming-scala" % "1.9.0" ,
  //Json parsing library
  "org.json4s" %% "json4s-native" % "3.6.10",
  //Cassandra
  "org.apache.flink" %% "flink-connector-cassandra" % "1.9.0"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

Global / onChangedBuildSource := IgnoreSourceChanges