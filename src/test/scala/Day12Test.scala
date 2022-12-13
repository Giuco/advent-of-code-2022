class Day12Test extends munit.FunSuite {
  val rawInput: String =
    """Sabqponm
      |abcryxxl
      |accszExk
      |acctuvwj
      |abdefghi""".stripMargin
  val parsedInput: List[List[Char]] = List(
    List('S', 'a', 'b', 'q', 'p', 'o', 'n', 'm'),
    List('a', 'b', 'c', 'r', 'y', 'x', 'x', 'l'),
    List('a', 'c', 'c', 's', 'z', 'E', 'x', 'k'),
    List('a', 'c', 'c', 't', 'u', 'v', 'w', 'j'),
    List('a', 'b', 'd', 'e', 'f', 'g', 'h', 'i'),
  )
  val puzzle: Day12 = Day12()

  test("parses input") {
    assertEquals(
      puzzle.parseInput(rawInput).map(_.toList).toList,
      parsedInput.map(_.toList).toList,
    )
  }

  test("test traversable") {
    println("1")
    //    assertEquals(
    //      puzzle.isTraversable(parsedInput, Point(0, 0), Point(-1, 0)),
    //      false
    //    )

    println("2")
    assertEquals(
      puzzle.isTraversable(parsedInput, Point(0, 1), Point(0, 1)),
      true
    )

    println("3")
    assertEquals(
      puzzle.isTraversable(parsedInput, Point(2, 0), Point(2, 1)),
      false
    )
  }

  test("problem 1") {
    assertEquals(
      puzzle.problem1(parsedInput),
      31
    )
  }
}
