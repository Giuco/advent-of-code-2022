type Matrix[A] = List[List[A]]

class Day08 extends Puzzle[Matrix[Int], Int] {
  val puzzleId = "08"

  override def parseInput(input: String): Matrix[Int] =
    input.split("\n").map(_.split("").map(_.toInt).toList).toList

  override def problem1(input: Matrix[Int]): Int =
    val visibilityMatrix = makeVisibilityMatrix(input)
    visibilityMatrix
      .map(_.map((n: Boolean) => if (n) 1 else 0))
      .map(_.sum)
      .sum

  def makeVisibilityMatrix(m: Matrix[Int]): Matrix[Boolean] =
    val left = isVisibleFromLeft(m)
    val right = isVisibleFromRight(m)
    val up = isVisibleFromUp(m)
    val down = isVisibleFromDown(m)

    left
      .zip(right).map((v1, v2) => v1.zip(v2).map(_ || _))
      .zip(up).map((v1, v2) => v1.zip(v2).map(_ || _))
      .zip(down).map((v1, v2) => v1.zip(v2).map(_ || _))

  def isVisibleFromLeft(m: Matrix[Int]): Matrix[Boolean] =
    m.map(makeVisibilityVector)

  def isVisibleFromRight(m: Matrix[Int]): Matrix[Boolean] =
    m.map(_.reverse).map(makeVisibilityVector).map(_.reverse)

  def isVisibleFromUp(m: Matrix[Int]): Matrix[Boolean] =
    m.transpose.map(makeVisibilityVector).transpose

  def isVisibleFromDown(m: Matrix[Int]): Matrix[Boolean] =
    m.reverse.transpose.map(makeVisibilityVector).transpose.reverse

  def makeVisibilityVector(v: List[Int]): List[Boolean] =
  // TODO: This function legibility is terrible
    v.foldLeft((List[Boolean](), -1))((a, n) => (a._1 :+ (n > a._2), a._2.max(n)))._1

  override def problem2(input: Matrix[Int]): Int =
    val nVisibleTress = for (
      i <- input.indices;
      j <- input.head.indices
    ) yield countVisibleTreesFromPoint(input, i, j)

    nVisibleTress.map(_ * _ * _ * _).max

  def countVisibleTreesFromPoint(m: Matrix[Int], i: Int, j: Int): (Int, Int, Int, Int) =
    val origin = m(i)(j)
    val upVector = m.take(i).map(_(j)).reverse
    val leftVector = m(i).take(j).reverse
    val rightVector = m(i).drop(j + 1)
    val downVector = m.drop(i + 1).map(_(j))

    val idx = List(
      (upVector.indexWhere(_ >= origin), upVector.size),
      (leftVector.indexWhere(_ >= origin), leftVector.size),
      (rightVector.indexWhere(_ >= origin), rightVector.size),
      (downVector.indexWhere(_ >= origin), downVector.size),
    )

    val nTress = idx.map((i, s) => if (i == -1) s else i + 1)

    (nTress(0), nTress(1), nTress(2), nTress(3))
}

object Day08 extends App {
  Day08().printSolutions()
}