package com.mocyuto.utils

import breeze.linalg.DenseMatrix

/**
 * Created by Yuto Suzuki on 2016/01/28.
 */
object DenseMatrixUtils {
  def identify(i: Int): DenseMatrix[Double] = DenseMatrix.eye[Double](i)
}
