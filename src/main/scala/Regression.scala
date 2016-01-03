import java.io.File
import breeze.linalg._

object Regression {
  def main(args: Array[String]): Unit = {
    if (args.length == 0) throw new Exception
    else {
      val mat = csvread(new File(args(0)))
      val y = mat(::, 14)
      val X1 = mat(::, 1 to 13)
      val X = DenseMatrix.horzcat(DenseMatrix.tabulate(mat.rows, 1) { case _ => 1.0 }, X1)
      val beta = (inv(X.t * X) * X.t) * y
      print(beta)
    }
  }
}
