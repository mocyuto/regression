package com.mocyuto

import java.io.File

import breeze.linalg._
import com.mocyuto.response.RegressionResponse

object LinearRegression extends Regression {

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

  /**
   * calculate regression
   * @param y Objective variables
   * @param XSeq Explanatory variables by Sequence of DenseVector
   * @return DenseVector of regression coefficient
   */
  def run(y: DenseVector[Double], XSeq: DenseVector[Double]*): RegressionResponse = {
    val matSeq = XSeq.map(_.toDenseMatrix)
    val X = DenseMatrix.horzcat(matSeq: _*)
    run(y, X)
  }

  /**
   * calculate regression
   * @param y Objective variables
   * @param X Explanatory variables by DenseMatrix
   * @return DenseVector of regression coefficient
   */
  def run(y: DenseVector[Double], X: DenseMatrix[Double]): RegressionResponse =
    RegressionResponse(coefficients = (inv(X.t * X) * X.t) * y, y = y, X = X)

}
