class Day06 extends Puzzle[List[Char], Int] {
  val puzzleId = "06"

  override def parseInput(input: String): List[Char] = input.toList

  def findFirstUnique(input: List[Char], k: Int): Int =
    k + input
      .sliding(k)
      .zipWithIndex
      .dropWhile((l: List[Char], i: Int) => l.length != l.distinct.length)
      .toList
      .head
      ._2

  override def problem1(input: List[Char]): Int =
    findFirstUnique(input, 4)

  override def problem2(input: List[Char]): Int =
    findFirstUnique(input, 14)
}

object Day06 extends App {
  Day06().printSolutions()
}