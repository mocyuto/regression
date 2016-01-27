package com.mocyuto.commonsmath

import breeze.linalg._
import com.mocyuto.Regression
import com.mocyuto.response.RegressionResponse
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression

import scala.collection.mutable.ArrayBuffer

/**
 * Created by Yuto Suzuki on 2016/01/27.
 */
object OLSLinearRegression extends Regression {

  def run(y: DenseVector[Double], X: DenseMatrix[Double]): RegressionResponse = {
    val regression = new OLSMultipleLinearRegression
    val yArr = y.toArray
    val xMax = {
      val array = new ArrayBuffer[Array[Double]]()
      for (row <- 0 to X.rows - 1) {
        array += X(::, row).toArray
      }
      array.result()
    }
    regression.newSampleData(yArr, xMax.toArray)
    val coef = regression.estimateRegressionParameters()
    RegressionResponse(coefficients = DenseVector(coef), y = y, X = X)
  }
}
