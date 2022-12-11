class Day01 extends Puzzle[List[List[Int]], Int, Int] {
  val puzzleId = "01"

  def parseInput(input: String): List[List[Int]] =
    input
      .split("\n\n")
      .map(_.split("\n"))
      .map(_.map(_.strip().toInt).toList)
      .toList

  def problem1(input: List[List[Int]]): Int =
    getTopNCalories(input, n = 1).sum

  def problem2(input: List[List[Int]]): Int =
    getTopNCalories(input, n = 3).sum

  def getTopNCalories(calories: List[List[Int]], n: Int): List[Int] =
    calories.map(_.sum).sorted.takeRight(n)
}

object Day01 extends App {
  val puzzle = Day01()
  puzzle.printSolutions()
}
