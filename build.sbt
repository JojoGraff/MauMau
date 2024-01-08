val scala3Version = "3.3.1"
val AkkaVersion = "2.9.0"

val dependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.17" % Test,
  "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test, "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5", "ch.qos.logback" % "logback-classic" % "1.4.7",
  // external dsl
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0",
  // akka streams alpaca
  "com.lightbend.akka" %% "akka-stream-alpakka-file" % "7.0.1",
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  // kafka
  "com.typesafe.akka" %% "akka-stream-kafka" % "4.0.2",
  // json serializer
  "io.circe" %% "circe-core" % "0.15.0-M1",
  "io.circe" %% "circe-generic" % "0.15.0-M1",
  "io.circe" %% "circe-parser" % "0.15.0-M1",
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "MauMau",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= dependencies ,
    resolvers += "Akka library repository".at("https://repo.akka.io/maven")
  )
