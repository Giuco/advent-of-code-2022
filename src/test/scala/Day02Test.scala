val test02Input ="""|A Y
    |B X
    |C Z""".stripMargin

val test02ParsedInput: List[(String, String)] = List(
  ("A", "Y"),
  ("B", "X"),
  ("C", "Z"),
)

class Day02Test extends munit.FunSuite {
  val puzzle = Day02()
  test("parses row correctly") {
    assertEquals(puzzle.parseRow("A Y"), ("A", "Y"))
  }

  test("correctly parse input") {
    assertEquals(puzzle.parseInput(test02Input), test02ParsedInput)
  }

  test("gets match winner") {
    assertEquals(puzzle.getWinner("A", "Y"), 1)
    assertEquals(puzzle.getWinner("B", "X"), -1)
    assertEquals(puzzle.getWinner("C", "Z"), 0)
  }

  test("count match points") {
    assertEquals(puzzle.countPoints("A", "Y"), 8)
    assertEquals(puzzle.countPoints("B", "X"), 1)
    assertEquals(puzzle.countPoints("C", "Z"), 6)
  }

  test("problem 1") {
    assertEquals(puzzle.problem1(test02ParsedInput), 15)
  }

  test("get correct hand") {
    assertEquals(puzzle.getCorrectHand("A", "Y"), "R")
    assertEquals(puzzle.getCorrectHand("B", "X"), "R")
    assertEquals(puzzle.getCorrectHand("C", "Z"), "R")
  }

  test("problem 2") {
    assertEquals(puzzle.problem2(test02ParsedInput), 12)
  }
}
