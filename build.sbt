scalaVersion := "2.12.20"

version := "1.0"
name := "Hello"
organization := "com.example"

Compile/mainClass := Some("com.example.Hello")

libraryDependencies += "org.scalameta" %% "munit" % "1.1.1" % Test
