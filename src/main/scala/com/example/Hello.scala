package com.example

import org.apache.spark.sql.SparkSession

import scala.io.Source

object Hello extends App {
  val source = Source
    .fromResource("customers-2000000.csv")
    .getLines()

  val spark = SparkSession
    .builder()
    .appName("Hello")
    .enableHiveSupport()
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val df = spark.read.csv("customers-2000000.csv")

  df.groupBy("Country", "City")
    .count()
    .orderBy("Country", "City")
    .show(Int.MaxValue)
}
