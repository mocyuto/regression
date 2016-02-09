package com.mocyuto

import breeze.linalg._
import breeze.numerics.sin
import org.scalatest.FlatSpec

/**
 * Created by yuto on 2016/02/06.
 */
class LinearRegressionTest extends FlatSpec {
  "linearRegression" should "Regress a line with sinusoidal noise" in {
    val lambda = 0.0
    val X = linspace(0, 100, 100).toDenseMatrix
    val yTemp: DenseVector[Double] = linspace(0, 100, 100) * 0.2 + 10.0
    val y = yTemp + sin(linspace(0, 100, 100))
    val res = LinearRegression.run(lambda, y, X)
    println(res.intercept)
    assert(res.coefficients(0) === 0.19644990055858422)
  }
}
