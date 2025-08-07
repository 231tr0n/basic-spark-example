package com.example

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StructType

import scala.io.Source

object Hello extends App {
  def createDataFrame(
      spark: SparkSession,
      data: Either[Seq[Row], org.apache.spark.rdd.RDD[Row]]
  ): DataFrame = {
    val schema = StructType(
      Seq(
        StructField("index", StringType, true),
        StructField("customer_id", StringType, true),
        StructField("first_name", StringType, true),
        StructField("last_name", StringType, true),
        StructField("company", StringType, true),
        StructField("city", StringType, true),
        StructField("country", StringType, true),
        StructField("phone_1", StringType, true),
        StructField("phone_2", StringType, true),
        StructField("email", StringType, true),
        StructField("subscription_date", StringType, true),
        StructField("website", StringType, true)
      )
    )

    data match {
      case Left(rowsSeq) =>
        spark.createDataFrame(spark.sparkContext.parallelize(rowsSeq), schema)
      case Right(rdd) => spark.createDataFrame(rdd, schema)
    }
  }

  val spark: SparkSession = SparkSession
    .builder()
    .appName("Hello")
    .enableHiveSupport()
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  var df: DataFrame =
    createDataFrame(spark, Right(spark.sparkContext.emptyRDD[Row]))

  val rows: Seq[Row] = Source
    .fromResource("customers-500000.csv")
    .getLines()
    .map(_.split(",").map(_.trim).toSeq)
    .map(line => Row.fromSeq(line))
    .toSeq

  df = df.unionByName(
    createDataFrame(
      spark,
      Left(rows)
    )
  )

  df.show()

  df.groupBy("Country", "City")
    .count()
    .orderBy("Country", "City")
    .show(Int.MaxValue)
}
