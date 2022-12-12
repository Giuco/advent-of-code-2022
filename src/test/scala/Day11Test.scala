class Day11Test extends munit.FunSuite {
  val rawInput =
    """Monkey 0:
      |  Starting items: 79, 98
      |  Operation: new = old * 19
      |  Test: divisible by 23
      |    If true: throw to monkey 2
      |    If false: throw to monkey 3
      |
      |Monkey 1:
      |  Starting items: 54, 65, 75, 74
      |  Operation: new = old + 6
      |  Test: divisible by 19
      |    If true: throw to monkey 2
      |    If false: throw to monkey 0
      |
      |Monkey 2:
      |  Starting items: 79, 60, 97
      |  Operation: new = old * old
      |  Test: divisible by 13
      |    If true: throw to monkey 1
      |    If false: throw to monkey 3
      |
      |Monkey 3:
      |  Starting items: 74
      |  Operation: new = old + 3
      |  Test: divisible by 17
      |    If true: throw to monkey 0
      |    If false: throw to monkey 1""".stripMargin
  
  val parsedInput = List(
    Monkey(
      id = 0,
      items = List(
        79,
        98
      ),
      operation = "old * 19",
      test = MonkeyTest(
        divisibleBy = 23,
        monkeyTrue = 2,
        monkeyFalse = 3
      )
    ),
    Monkey(
      id = 1,
      items = List(
        54,
        65,
        75,
        74
      ),
      operation = "old + 6",
      test = MonkeyTest(
        divisibleBy = 19,
        monkeyTrue = 2,
        monkeyFalse = 0
      )
    ),
    Monkey(
      id = 2,
      items = List(
        79,
        60,
        97
      ),
      operation = "old * old",
      test = MonkeyTest(
        divisibleBy = 13,
        monkeyTrue = 1,
        monkeyFalse = 3
      )
    ),
    Monkey(
      id = 3,
      items = List(
        74
      ),
      operation = "old + 3",
      test = MonkeyTest(
        divisibleBy = 17,
        monkeyTrue = 0,
        monkeyFalse = 1
      )
    )
  )

  val puzzle = Day11()


  test("parse monkey test") {
    assertEquals(
      MonkeyTest.parse(
        """Test: divisible by 23
          |    If true: throw to monkey 2
          |    If false: throw to monkey 3""".stripMargin),
      MonkeyTest(
        divisibleBy = 23,
        monkeyTrue = 2,
        monkeyFalse = 3
      )
    )
  }

  test("parse monkey") {
    assertEquals(
      Monkey.parse(rawInput.split("\n\n").head.strip()),
      parsedInput.head
    )
  }

  test("parse input") {
    assertEquals(
      puzzle.parseInput(rawInput),
      parsedInput,
    )
  }
  
  test("run monkey operation") {
    val monkey = parsedInput.head
    assertEquals(
      monkey.runOperation(10),
      BigInt(190)
    )
  }
  
  test("run monkey operation two vars") {
    val monkey = parsedInput(2)
    assertEquals(
      monkey.runOperation(12),
      BigInt(144)
    )
  } 
  
  test("monkey test divisible") {
    val monkey = parsedInput.head
    assertEquals(
      monkey.test.monkeyToThrow(46),
      2
    )
    
    assertEquals(
      monkey.test.monkeyToThrow(47),
      3
    )
  }
  
  test("monkey turn") {
    val expectedState = List(
      Monkey(
        id = 0,
        items = List(),
        operation = "old * 19",
        test = MonkeyTest(
          divisibleBy = 23,
          monkeyTrue = 2,
          monkeyFalse = 3
        )
      ),
      Monkey(
        id = 1,
        items = List(
          54,
          65,
          75,
          74
        ),
        operation = "old + 6",
        test = MonkeyTest(
          divisibleBy = 19,
          monkeyTrue = 2,
          monkeyFalse = 0
        )
      ),
      Monkey(
        id = 2,
        items = List(
          79,
          60,
          97
        ),
        operation = "old * old",
        test = MonkeyTest(
          divisibleBy = 13,
          monkeyTrue = 1,
          monkeyFalse = 3
        )
      ),
      Monkey(
        id = 3,
        items = List(
          74,
          500,
          620
        ),
        operation = "old + 3",
        test = MonkeyTest(
          divisibleBy = 17,
          monkeyTrue = 0,
          monkeyFalse = 1
        )
      )
    )
    
    assertEquals(
      puzzle.runMonkeyTurn(parsedInput, 0)._1,
      expectedState
    )
  }
  
  test("test running a round") {
    val expectedState = List(
      Monkey(
        id = 0,
        items = List(
          20,
          23,
          27,
          26
        ),
        operation = "old * 19",
        test = MonkeyTest(
          divisibleBy = 23,
          monkeyTrue = 2,
          monkeyFalse = 3
        )
      ),
      Monkey(
        id = 1,
        items = List(
          2080,
          25,
          167,
          207,
          401,
          1046,
        ),
        operation = "old + 6",
        test = MonkeyTest(
          divisibleBy = 19,
          monkeyTrue = 2,
          monkeyFalse = 0
        )
      ),
      Monkey(
        id = 2,
        items = List(),
        operation = "old * old",
        test = MonkeyTest(
          divisibleBy = 13,
          monkeyTrue = 1,
          monkeyFalse = 3
        )
      ),
      Monkey(
        id = 3,
        items = List(),
        operation = "old + 3",
        test = MonkeyTest(
          divisibleBy = 17,
          monkeyTrue = 0,
          monkeyFalse = 1
        )
      )
    )
    
    assertEquals(
      puzzle.runRound(parsedInput, List.fill(parsedInput.size)(0))._1,
      expectedState
    )
  }
  
  test("test inspection count (20 rounds)") {
    val expectedInspectionCount = List(BigInt(101), BigInt(95), BigInt(7), BigInt(105))
    assertEquals(
      puzzle.runNRounds(parsedInput, 20)._2,
      expectedInspectionCount
    )
  }
  
  test("problem 1") {
    assertEquals(
      puzzle.problem1(parsedInput),
      BigInt(10605)
    )
  }
  
  test("test inspection count (10,000 rounds)") {
    val expectedInspectionCount = List(BigInt(52166), BigInt(47830), BigInt(1938), BigInt(52013))
    assertEquals(
      puzzle.runNRounds(parsedInput, 10000)._2,
      expectedInspectionCount
    )
    
  }
  
  test("problem 2") {
    assertEquals(
      puzzle.problem2(parsedInput),
      BigInt(2713310158L)
    )
  }
}
