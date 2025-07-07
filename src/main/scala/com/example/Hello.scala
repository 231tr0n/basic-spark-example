package com.example

import scala.io.Source

object Hello extends App {
  Source
    .fromResource("customers-2000000.csv")
    .getLines()
    .take(10)
    .foreach(println)
}
