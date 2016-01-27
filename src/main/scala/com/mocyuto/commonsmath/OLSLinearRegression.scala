package com.mocyuto.commonsmath

import breeze.linalg._
import com.mocyuto.Regression
import com.mocyuto.response.RegressionResponse
import com.mocyuto.utils.DenseVectorUtils._
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
      println(s"rows: ${X.rows}")
      for (row <- 1 to X.rows) {
        val x = X(row - 1, ::).t
        if (!x.isZeroVector) array += x.toArray
      }
      array.result()
    }.toArray
    xMax.foreach { xs =>
      xs.foreach(x => print(s"$x,"))
      println()
    }
    regression.newSampleData(yArr, xMax)
    val coef = regression.estimateRegressionParameters()
    RegressionResponse(coefficients = DenseVector(coef), y = y, X = X)
  }
}
