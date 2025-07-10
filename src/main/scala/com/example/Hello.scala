package com.example

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SparkSession
import scala.io.Source
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.IntegerType
import org.apache.avro.generic.GenericData.StringType

object Hello extends App {
  val spark: SparkSession = SparkSession
    .builder()
    .appName("Hello")
    .enableHiveSupport()
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  var headerRead = false

  var df = spark.createDataFrame(
    spark.sparkContext.emptyRDD[Row],
    StructType(
      Seq(
        StructField("CustomerID", IntegerType, true),
        StructField("FirstName", StringType, true),
        StructField("LastName", StringType, true),
        StructField("Country", StringType, true),
        StructField("City", StringType, true)
      )
    )
  )

  Source
    .fromResource("customers.csv")
    .getLines()
    .foreach(line => {
      if (!headerRead) {
        headerRead = true
      } else {
        val columns = line.split(",").map(_.trim)
      }
    })

  // val df: DataFrame = spark.read
  //   .format("csv")
  //   .option("inferSchema", "true")
  //   .option("header", "true")
  //   .load("src/main/resources/customers.csv")
  //
  // df.groupBy("Country", "City")
  //   .count()
  //   .orderBy("Country", "City")
  //   .show(Int.MaxValue)
}
