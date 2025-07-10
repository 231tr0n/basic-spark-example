scalaVersion := "2.13.16"

version := "1.0"
name := "Hello"
organization := "com.example"

Compile / mainClass := Some("com.example.Hello")

libraryDependencies += "org.scalameta" %% "munit" % "1.1.1" % Test
libraryDependencies += "org.apache.spark" %% "spark-core" % "4.0.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "4.0.0" % "provided"
