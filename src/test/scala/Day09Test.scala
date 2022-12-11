class Day09Test extends munit.FunSuite {
  val rawInput =
    """R 4
      |U 4
      |L 3
      |D 1
      |R 4
      |D 1
      |L 5
      |R 2""".stripMargin

  val parsedInput = List(
    Mov('R', 4),
    Mov('U', 4),
    Mov('L', 3),
    Mov('D', 1),
    Mov('R', 4),
    Mov('D', 1),
    Mov('L', 5),
    Mov('R', 2),
  )

  val puzzle = Day09()

  test("parsing input") {
    assertEquals(
      puzzle.parseInput(rawInput),
      parsedInput
    )
  }

  test("single move") {
    assertEquals(
      puzzle.singleMove(
        'U',
        List(
          Pos(1, 0),
          Pos(0, 0),
          Pos(-1, 0)
        )
      ),
      List(
        Pos(2, 0),
        Pos(1, 0),
        Pos(0, 0)
      )
    )
  }

  test("problem 1") {
    assertEquals(
      puzzle.problem1(parsedInput),
      13
    )
  }

  test("problem 2 (example 1)") {
    assertEquals(
      puzzle.problem2(parsedInput),
      1
    )
  }

  test("problem 2 (example 2)") {
    val rawInput2 =
      """R 5
        |U 8
        |L 8
        |D 3
        |R 17
        |D 10
        |L 25
        |U 20""".stripMargin
    val parsedInput2 = puzzle.parseInput(rawInput2)

    assertEquals(
      puzzle.problem2(parsedInput2),
      36
    )
  }
}
