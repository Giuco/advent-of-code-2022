case class IntervalPair(a: (Int, Int), b: (Int, Int)) {}

class Day04 extends Puzzle[List[IntervalPair], Int, Int] {
  val puzzleId = "04"

  def parseRow(input: String): IntervalPair =
    val pair = input.split(",")
    val values1 = pair(0).split("-")
    val values2 = pair(1).split("-")
    IntervalPair(
      (values1(0).toInt, values1(1).toInt),
      (values2(0).toInt, values2(1).toInt)
    )

  override def parseInput(input: String): List[IntervalPair] =
    input
      .split("\n")
      .map(parseRow)
      .toList

  def isContained(a: (Int, Int), b: (Int, Int)): Boolean =
    b._1 >= a._1 && b._2 <= a._2

  def doesOverlap(a: (Int, Int), b: (Int, Int)): Boolean =
    b._2 >= a._1 && b._1 <= a._2

  override def problem1(input: List[IntervalPair]): Int =
    input
      .map((i: IntervalPair) => isContained(i.a, i.b) || isContained(i.b, i.a))
      .map(if (_) 1 else 0)
      .sum

  override def problem2(input: List[IntervalPair]): Int =
    input
      .map((i: IntervalPair) => doesOverlap(i.a, i.b))
      .map(if (_) 1 else 0)
      .sum
}


object Day04 extends App {
  Day04().printSolutions()
}