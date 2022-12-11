class Day10Test extends munit.FunSuite {
  val rawInput =
    """addx 15
      |addx -11
      |addx 6
      |addx -3
      |addx 5
      |addx -1
      |addx -8
      |addx 13
      |addx 4
      |noop
      |addx -1
      |addx 5
      |addx -1
      |addx 5
      |addx -1
      |addx 5
      |addx -1
      |addx 5
      |addx -1
      |addx -35
      |addx 1
      |addx 24
      |addx -19
      |addx 1
      |addx 16
      |addx -11
      |noop
      |noop
      |addx 21
      |addx -15
      |noop
      |noop
      |addx -3
      |addx 9
      |addx 1
      |addx -3
      |addx 8
      |addx 1
      |addx 5
      |noop
      |noop
      |noop
      |noop
      |noop
      |addx -36
      |noop
      |addx 1
      |addx 7
      |noop
      |noop
      |noop
      |addx 2
      |addx 6
      |noop
      |noop
      |noop
      |noop
      |noop
      |addx 1
      |noop
      |noop
      |addx 7
      |addx 1
      |noop
      |addx -13
      |addx 13
      |addx 7
      |noop
      |addx 1
      |addx -33
      |noop
      |noop
      |noop
      |addx 2
      |noop
      |noop
      |noop
      |addx 8
      |noop
      |addx -1
      |addx 2
      |addx 1
      |noop
      |addx 17
      |addx -9
      |addx 1
      |addx 1
      |addx -3
      |addx 11
      |noop
      |noop
      |addx 1
      |noop
      |addx 1
      |noop
      |noop
      |addx -13
      |addx -19
      |addx 1
      |addx 3
      |addx 26
      |addx -30
      |addx 12
      |addx -1
      |addx 3
      |addx 1
      |noop
      |noop
      |noop
      |addx -9
      |addx 18
      |addx 1
      |addx 2
      |noop
      |noop
      |addx 9
      |noop
      |noop
      |noop
      |addx -1
      |addx 2
      |addx -37
      |addx 1
      |addx 3
      |noop
      |addx 15
      |addx -21
      |addx 22
      |addx -6
      |addx 1
      |noop
      |addx 2
      |addx 1
      |noop
      |addx -10
      |noop
      |noop
      |addx 20
      |addx 1
      |addx 2
      |addx 2
      |addx -6
      |addx -11
      |noop
      |noop
      |noop""".stripMargin
  val parsedInput = List(Instruction(InstructionType.ADDX, Some(15)), Instruction(InstructionType.ADDX, Some(-11)), Instruction(InstructionType.ADDX, Some(6)), Instruction(InstructionType.ADDX, Some(-3)), Instruction(InstructionType.ADDX, Some(5)), Instruction(InstructionType.ADDX, Some(-1)), Instruction(InstructionType.ADDX, Some(-8)), Instruction(InstructionType.ADDX, Some(13)), Instruction(InstructionType.ADDX, Some(4)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(-1)), Instruction(InstructionType.ADDX, Some(5)), Instruction(InstructionType.ADDX, Some(-1)), Instruction(InstructionType.ADDX, Some(5)), Instruction(InstructionType.ADDX, Some(-1)), Instruction(InstructionType.ADDX, Some(5)), Instruction(InstructionType.ADDX, Some(-1)), Instruction(InstructionType.ADDX, Some(5)), Instruction(InstructionType.ADDX, Some(-1)), Instruction(InstructionType.ADDX, Some(-35)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(24)), Instruction(InstructionType.ADDX, Some(-19)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(16)), Instruction(InstructionType.ADDX, Some(-11)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(21)), Instruction(InstructionType.ADDX, Some(-15)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(-3)), Instruction(InstructionType.ADDX, Some(9)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(-3)), Instruction(InstructionType.ADDX, Some(8)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(5)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(-36)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(7)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(2)), Instruction(InstructionType.ADDX, Some(6)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(7)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(-13)), Instruction(InstructionType.ADDX, Some(13)), Instruction(InstructionType.ADDX, Some(7)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(-33)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(2)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(8)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(-1)), Instruction(InstructionType.ADDX, Some(2)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(17)), Instruction(InstructionType.ADDX, Some(-9)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(-3)), Instruction(InstructionType.ADDX, Some(11)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(-13)), Instruction(InstructionType.ADDX, Some(-19)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(3)), Instruction(InstructionType.ADDX, Some(26)), Instruction(InstructionType.ADDX, Some(-30)), Instruction(InstructionType.ADDX, Some(12)), Instruction(InstructionType.ADDX, Some(-1)), Instruction(InstructionType.ADDX, Some(3)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(-9)), Instruction(InstructionType.ADDX, Some(18)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(2)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(9)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(-1)), Instruction(InstructionType.ADDX, Some(2)), Instruction(InstructionType.ADDX, Some(-37)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(3)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(15)), Instruction(InstructionType.ADDX, Some(-21)), Instruction(InstructionType.ADDX, Some(22)), Instruction(InstructionType.ADDX, Some(-6)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(2)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(-10)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.ADDX, Some(20)), Instruction(InstructionType.ADDX, Some(1)), Instruction(InstructionType.ADDX, Some(2)), Instruction(InstructionType.ADDX, Some(2)), Instruction(InstructionType.ADDX, Some(-6)), Instruction(InstructionType.ADDX, Some(-11)), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None), Instruction(InstructionType.NOOP, None))

  val puzzle = Day10()

  test("parse row add x") {
    assertEquals(
      puzzle.parseRow("addx 10"),
      Instruction(InstructionType.ADDX, Some(10))
    )
  }

  test("parse row noop") {
    assertEquals(
      puzzle.parseRow("noop"),
      Instruction(InstructionType.NOOP, None)
    )
  }

  test("parse input") {
    assertEquals(
      puzzle.parseInput(rawInput),
      parsedInput
    )
  }

  test("problem 1") {
    assertEquals(
      puzzle.problem1(parsedInput),
      13140
    )
  }

  test("problem 2") {
    val expectedOutput =
      """|##..##..##..##..##..##..##..##..##..##..
         |###...###...###...###...###...###...###.
         |####....####....####....####....####....
         |#####.....#####.....#####.....#####.....
         |######......######......######......####
         |#######.......#######.......#######.....""".stripMargin

    assertEquals(
      puzzle.problem2(parsedInput),
      expectedOutput
    )
  }
}
