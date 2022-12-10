class Day08Test extends munit.FunSuite {
  val rawInput =
    """30373
      |25512
      |65332
      |33549
      |35390""".stripMargin
  val parsedInput = List(
    List(3, 0, 3, 7, 3),
    List(2, 5, 5, 1, 2),
    List(6, 5, 3, 3, 2),
    List(3, 3, 5, 4, 9),
    List(3, 5, 3, 9, 0),
  )
  val puzzle = Day08()
  
  test("parse input") {
    assertEquals(
      puzzle.parseInput(rawInput),
      parsedInput
    )
  }
  
  test("is visible from left") {
    val expectedOutput = List(
      List(true, false, false, true, false),
      List(true, true, false, false, false),
      List(true, false, false, false, false),
      List(true, false, true, false, true),
      List(true, true, false, true, false),
    )
    
    assertEquals(
      puzzle.isVisibleFromLeft(parsedInput),
      expectedOutput
    )
  }
  
  test("is visible from right") {
    val expectedOutput = List(
      List(false, false, false, true, true),
      List(false, false, true, false, true),
      List(true, true, false, true, true),
      List(false, false, false, false, true),
      List(false, false, false, true, true),
    )
    
    assertEquals(
      puzzle.isVisibleFromRight(parsedInput),
      expectedOutput,
    )
  }
  
  test("is visible from up") {
    val expectedOutput = List(
      List(true, true, true, true, true),
      List(false, true, true, false, false),
      List(true, false, false, false, false),
      List(false, false, false, false, true),
      List(false, false, false, true, false),
    )
    
    assertEquals(
      puzzle.isVisibleFromUp(parsedInput),
      expectedOutput,
    )
  }

  test("is visible from down") {
    val expectedOutput = List(
      List(false, false, false, false, false),
      List(false, false, false, false, false),
      List(true, false, false, false, false),
      List(false, false, true, false, true),
      List(true, true, true, true, true),
    )

    assertEquals(
      puzzle.isVisibleFromDown(parsedInput),
      expectedOutput,
    )
  }
  
  test("is visible") {
    val expectedOutput = List(
      List(true, true, true, true, true),
      List(true, true, true, false, true),
      List(true, true, false, true, true),
      List(true, false, true, false, true),
      List(true, true, true, true, true),
    )
    assertEquals(
      puzzle.makeVisibilityMatrix(parsedInput),
      expectedOutput
    )
  }
  
  test("problem 1") {
    assertEquals(
      puzzle.problem1(parsedInput),
      21
    )
  }
  
  test("count visible trees from point") {
    assertEquals(
      puzzle.countVisibleTreesFromPoint(parsedInput, 1, 2),
      (1, 1, 2, 2)
    )
  
    assertEquals(
      puzzle.countVisibleTreesFromPoint(parsedInput, 3, 2),
      (2, 2, 2, 1)
    )
  }
  
  test("count visible trees from point (giving trouble") {
    assertEquals(
      puzzle.countVisibleTreesFromPoint(parsedInput, 2, 3),
      (2, 1, 1, 1)
    )
  }
  
  test("problem 2") {
    assertEquals(
      puzzle.problem2(parsedInput),
      8
    )
  }
}
