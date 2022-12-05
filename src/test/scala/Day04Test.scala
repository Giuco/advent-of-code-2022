val test04Input: String =
  """2-4,6-8
    |2-3,4-5
    |5-7,7-9
    |2-8,3-7
    |6-6,4-6
    |2-6,4-8""".stripMargin

val test04ParsedInput = List(
  IntervalPair((2, 4), (6, 8)),
  IntervalPair((2, 3), (4, 5)),
  IntervalPair((5, 7), (7, 9)),
  IntervalPair((2, 8), (3, 7)),
  IntervalPair((6, 6), (4, 6)),
  IntervalPair((2, 6), (4, 8)),
)

class Day04Test extends munit.FunSuite {
  val puzzle = Day04()

  test("parse row") {
    assertEquals(
      puzzle.parseRow("2-4,6-8"),
      IntervalPair((2, 4), (6, 8))
    )
  }

  test("parse input") {
    assertEquals(
      puzzle.parseInput(test04Input),
      test04ParsedInput
    )
  }

  test("is contained") {
    assert(puzzle.isContained((3, 9), (5, 8)))
    assert(!puzzle.isContained((3, 9), (2, 8)))
  }

  test("problem 1") {
    assertEquals(
      puzzle.problem1(test04ParsedInput),
      2
    )
  }

  test("does overlap") {
    assert(puzzle.doesOverlap((3, 9), (5, 8)))
    assert(puzzle.doesOverlap((5, 8), (3, 9)))
    assert(puzzle.doesOverlap((3, 9), (2, 8)))
    assert(!puzzle.doesOverlap((3, 5), (6, 8)))
  }

  test("problem 2") {
    assertEquals(
      puzzle.problem2(test04ParsedInput),
      4
    )
  }
}
