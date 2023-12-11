val scala3Version = "3.3.1"
val AkkaVersion = "2.9.0"

val dependencies = Seq("org.scalatest" %% "scalatest" % "3.2.17" % "test") ++
  Seq("org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test, "org.scalatest" %% "scalatest" % "3.2.15" % Test) ++
  Seq("com.typesafe.scala-logging" %% "scala-logging" % "3.9.5", "ch.qos.logback" % "logback-classic" % "1.4.7") ++
  Seq("org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0") ++
  Seq(
    "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
    "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test
  )

resolvers += "Akka library repository".at("https://repo.akka.io/maven")
lazy val root = project
  .in(file("."))
  .settings(
    name := "MauMau",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= dependencies
  )
