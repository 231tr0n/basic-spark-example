package com.example

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SparkSession

import scala.io.Source

object Hello extends App {
  val source: Iterator[String] = Source
    .fromResource("customers-2000000.csv")
    .getLines()

  val spark: SparkSession = SparkSession
    .builder()
    .appName("Hello")
    .enableHiveSupport()
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val df: DataFrame = spark.read.csv("customers-2000000.csv")

  df.groupBy("Country", "City")
    .count()
    .orderBy("Country", "City")
    .show(Int.MaxValue)
}
