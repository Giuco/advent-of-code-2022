val test01Input = """1000
                    |2000
                    |3000
                    |
                    |4000
                    |
                    |5000
                    |6000
                    |
                    |7000
                    |8000
                    |9000
                    |
                    |10000 """.stripMargin
val test01ParsedInput = List(List(1000, 2000, 3000), List(4000), List(5000, 6000), List(7000, 8000, 9000), List(10000))

class Day01Test extends munit.FunSuite {
  val puzzle = Day01()
  test("parse input correctly") {
    val output = puzzle.parseInput(test01Input)
    assertEquals(output, test01ParsedInput)
  }

  test("get max of sum of arrays") {
    val max = puzzle.getTopNCalories(test01ParsedInput, 1)
    assertEquals(max, List(24000))
  }

  test("entire problem 1") {
    assertEquals(puzzle.problem1(test01ParsedInput), 24000)
  }

  test("take top 3 calories") {
    assertEquals(puzzle.getTopNCalories(test01ParsedInput, 3), List(10000, 11000, 24000))
  }

  test("entire problem 2") {
    assertEquals(puzzle.problem2(test01ParsedInput), 45000)
  }
}
