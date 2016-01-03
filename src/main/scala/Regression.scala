import java.io.File
import breeze.linalg._

object Regression {

  /**
   * CSVのフォーマット
   * Boston.csv(https://sense.io/csrudolflai/data-mining-with-r/files/Boston.csv)の形式を想定
   * index,data_1,data_2,...,data_n,y
   * @param args
   */
  def main(args: Array[String]): Unit = {
    if (args.length == 0) throw new Exception
    else {
      val beta = regression(new File(args(0)))
      println(beta)
    }
  }

  def regression(file: File): DenseVector[Double] = {
    val mat = csvread(file, skipLines = 1)
    val y = mat(::, mat.cols - 1)
    val X = DenseMatrix.horzcat(
      DenseMatrix.tabulate(mat.rows, 1) { case _ => 1.0 },
      mat(::, 1 to mat.cols - 2)
    )
    (inv(X.t * X) * X.t) * y
  }
}
