package com.mocyuto

import java.io.File

import breeze.linalg._
import com.mocyuto.response.RegressionResponse
import breeze.stats._

import scala.util.Try

object LinearRegression extends Regression {

  /**
   * calculate regression by CSV
   * @param file csv file
   * @return DenseVector of regression coefficient
   */
  def run(lambda: Double = 0, file: File): RegressionResponse = {
    val mat = csvread(file, skipLines = 1)
    val y = mat(::, mat.cols - 1)
    val X = DenseMatrix.horzcat(
      DenseMatrix.tabulate(mat.rows, 1) { case _ => 1.0 },
      mat(::, 1 to mat.cols - 2)
    )
    run(lambda, y, X)
  }

  /**
   * calculate regression
   * ex)
   * run(0, DenseVector(1,2), DenseMatrix(), DenseMatrix()...)
   * @param y Objective variables
   * @param XSeq Explanatory variables by Sequence of DenseVector
   * @return DenseVector of regression coefficient
   */
  def run(lambda: Double, y: DenseVector[Double], XSeq: DenseVector[Double]*): RegressionResponse = {
    val matSeq = XSeq.map(_.toDenseMatrix)
    val X = DenseMatrix.horzcat(matSeq: _*)
    run(lambda, y, X)
  }

  /**
   * calculate regression
   * @param lambda
   * @param y Objective variables
   * @param X Explanatory variables by DenseMatrix
   * @return DenseVector of regression coefficient
   */
  def run(lambda: Double, y: DenseVector[Double], X: DenseMatrix[Double]): RegressionResponse = {
    def powerInv(x: DenseMatrix[Double]) = inv(x.t * x)
    val inverse = Try(
      powerInv(X + lambda) * X.t * y
    ).getOrElse(DenseVector.tabulate(X.cols) { i => if (i == 0) mean(y) else 0 })

    RegressionResponse(coefficients = inverse, y = y, X = X)
  }

}
