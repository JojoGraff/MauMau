val scala3Version = "3.3.1"

val dependencies = Seq("org.scalatest" %% "scalatest" % "3.2.17" % "test") ++
  Seq(
    "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test,
    "org.scalatest" %% "scalatest" % "3.2.15" % Test
  )

lazy val root = project
  .in(file("."))
  .settings(
    name := "MauMau",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= dependencies
  )
