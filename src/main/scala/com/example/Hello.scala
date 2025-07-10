package com.example

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SparkSession

object Hello extends App {
  val spark: SparkSession = SparkSession
    .builder()
    .appName("Hello")
    .enableHiveSupport()
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val df: DataFrame = spark.read
    .format("csv")
    .option("inferSchema", "true")
    .option("header", "true")
    .load("src/main/resources/customers.csv")

  df.groupBy("Country", "City")
    .count()
    .orderBy("Country", "City")
    .show(Int.MaxValue)
}
