package com.mocyuto.utils

import breeze.linalg.DenseVector

/**
 * Created by Yuto Suzuki on 2016/01/27.
 */
object DenseVectorUtils {
  implicit class checkDenseVector(vector: DenseVector[Double]) {
    def isZeroVector = vector.forall(v => v == 0.0)
  }
}
