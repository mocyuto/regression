package com.mocyuto.response

import breeze.linalg._
import breeze.math._
import breeze.numerics._

/**
 * regression response
 * @param coefficients first row is intercept, others are coefficient order by input
 */
case class RegressionResponse(
    coefficients: DenseVector[Double],
    y: DenseVector[Double],
    X: DenseMatrix[Double]) {
  val intercept = coefficients(0)

  val fittedValues = X * coefficients
  lazy val residuals = y - fittedValues
  lazy val fStatistic = pow(fittedValues, 2) / (y.length - 1.0) / (residuals / (residuals.length - 1.0))

}
