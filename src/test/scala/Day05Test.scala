import scala.collection.mutable


class Day05Test extends munit.FunSuite {
  val rawInput: String =
    """    [D]    
      |[N] [C]    
      |[Z] [M] [P]
      | 1   2   3
      |
      |move 1 from 2 to 1
      |move 3 from 1 to 3
      |move 2 from 2 to 1
      |move 1 from 1 to 2""".stripMargin

  val parsedInput: Game = Game(
    stacks = List(
      List('N', 'Z'),
      List('D', 'C', 'M'),
      List('P')
    ),
    ops = List(
      Movement(1, 2, 1),
      Movement(3, 1, 3),
      Movement(2, 2, 1),
      Movement(1, 1, 2),
    )
  )

  val puzzle = Day05()

  test("parses movements") {
    val input =
      """move 1 from 2 to 1
        |move 3 from 1 to 3""".stripMargin

    val expectedOutput = List(Movement(1, 2, 1), Movement(3, 1, 3))

    assertEquals(puzzle.parseMovements(input), expectedOutput)
  }

  test("parses stacks") {
    val input =
      """    [D]    
        |[N] [C]    
        |[Z] [M] [P]
        | 1   2   3""".stripMargin
    val expectedOutput = List(
      List('N', 'Z'),
      List('D', 'C', 'M'),
      List('P')
    )

    assertEquals(puzzle.parseStacks(input), expectedOutput)
  }

  test("parses input") {
    assertEquals(
      puzzle.parseInput(rawInput),
      parsedInput
    )
  }

  test("test moves one crate correctly") {
    val inputStack = List(
      List('N', 'Z'),
      List('D', 'C', 'M'),
      List('P')
    )
    val inputMovement = Movement(1, 2, 1)
    val expectedStack = List(
      List('D', 'N', 'Z'),
      List('C', 'M'),
      List('P')
    )

    assertEquals(
      puzzle.doMove(inputStack, inputMovement, false),
      expectedStack
    )
  }

  test("test moving more than one crate") {
    val inputStack = List(
      List('N', 'Z'),
      List('D', 'C', 'M'),
      List('P')
    )
    val inputMovement = Movement(2, 2, 1)
    val expectedStack = List(
      List('C', 'D', 'N', 'Z'),
      List('M'),
      List('P')
    )

    assertEquals(
      puzzle.doMove(inputStack, inputMovement, false),
      expectedStack
    )
  }
  
  
  test("problem 1") {
    assertEquals(
      puzzle.problem1(parsedInput),
      "CMZ"
    )
  }

  test("problem 2") {
    assertEquals(
      puzzle.problem2(parsedInput),
      "MCD"
    )
  }
  
}
