class Day02 extends Puzzle[List[(String, String)], Int, Int]{
  val puzzleId = "02"
  val handMap = Map(
    "A" -> "R",
    "B" -> "P",
    "C" -> "S",

    "X" -> "R",
    "Y" -> "P",
    "Z" -> "S",

    "R" -> "R",
    "P" -> "P",
    "S" -> "S",
  )

  def parseRow(row: String): (String, String) =
    val splitted = row.split(" ", 2)
    (splitted(0), splitted(1))

  def parseInput(input: String): List[(String, String)] =
    input.split("\n").map(parseRow).toList

  def getWinner(a: String, b: String): Int =
    val handA = handMap(a)
    val handB = handMap(b)

    (handA, handB) match {
      case ("R", "R") => 0
      case ("P", "P") => 0
      case ("S", "S") => 0

      case ("R", "P") => 1
      case ("R", "S") => -1

      case ("P", "R") => -1
      case ("P", "S") => 1

      case ("S", "R") => 1
      case ("S", "P") => -1
    }

  def countPoints(a: String, b: String): Int =
    val handPoint = Map(
      "R" -> 1,
      "P" -> 2,
      "S" -> 3,
    )(handMap(b))
    val matchWinner = getWinner(a, b)

    val matchResultPoint = Map(
      1 -> 6,
      0 -> 3,
      -1 -> 0,
    )(matchWinner)

    handPoint + matchResultPoint

  def getCorrectHand(a: String, b: String): String =
    val handA = handMap(a)
    b match {
      case "X" => handA match {
        case "R" => "S"
        case "P" => "R"
        case "S" => "P"
      }
      case "Z" => handA match {
        case "R" => "P"
        case "P" => "S"
        case "S" => "R"
      }
      case "Y" => handA
    }

  def problem1(input: List[(String, String)]): Int =
    input.map(countPoints).sum

  def problem2(input: List[(String, String)]): Int =
    input
      .map((a: String, b: String) => (a, getCorrectHand(a, b)))
      .map(countPoints)
      .sum
}

object Day02 extends App {
  val puzzle = Day02()
  puzzle.printSolutions()
}
