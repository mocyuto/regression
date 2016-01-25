package com.mocyuto

import java.io.File

import com.mocyuto.response.RegressionResponse

/**
 * Created by Yuto Suzuki on 2016/01/25.
 */
trait Regression {
  /**
   * CSVのフォーマット
   * Boston.csv(https://sense.io/csrudolflai/data-mining-with-r/files/Boston.csv)の形式を想定
   * index,data_1,data_2,...,data_n,y
   * @param args
   */
  def main(args: Array[String]): Unit = {
    if (args.length == 0) throw new Exception
    else {
      val beta = run(new File(args(0))).coefficients
      beta.foreach(b => print(b))
    }
  }

  def run(file: File): RegressionResponse
}
