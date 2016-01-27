package com.mocyuto

import java.io.File

import breeze.linalg._
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

  /**
   * calculate regression by CSV
   * @param file csv file
   * @return DenseVector of regression coefficient
   */
  def run(file: File): RegressionResponse = {
    val mat = csvread(file, skipLines = 1)
    val y = mat(::, mat.cols - 1)
    val X = DenseMatrix.horzcat(
      DenseMatrix.tabulate(mat.rows, 1) { case _ => 1.0 },
      mat(::, 1 to mat.cols - 2)
    )
    run(y, X)
  }

  def run(y: DenseVector[Double], X: DenseMatrix[Double]): RegressionResponse
}
