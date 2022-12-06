trait Puzzle[I, R] {
  val puzzleId: String

  def parseInput(input: String): I

  def problem1(input: I): R

  def problem2(input: I): R

  def solveProblems(rawInput: String): (R, R) = {
    val parsedInput = parseInput(rawInput)
    val result1 = problem1(parsedInput)
    val result2 = problem2(parsedInput)
    (result1, result2)
  }

  def readInput(): String =
    scala.io.Source.fromFile(s"./data/$puzzleId.txt").mkString

  def printSolutions(): Unit =
    val rawInput = readInput()
    val (result1, result2) = solveProblems(rawInput)
    println(s"Day: $puzzleId")
    println(s"Problem 1: $result1")
    println(s"Problem 2: $result2")
}
