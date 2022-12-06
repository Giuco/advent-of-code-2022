class Day06Test extends munit.FunSuite {
  val rawInput = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
  val parsedInput = List('m', 'j', 'q', 'j', 'p', 'q', 'm', 'g', 'b', 'l', 'j', 's', 'p', 'h', 'd', 'z', 't', 'n', 'v', 'j', 'f', 'q', 'w', 'r', 'c', 'g', 's', 'm', 'l', 'b')
  val puzzle = Day06()

  test("parse input") {
    assertEquals(puzzle.parseInput(rawInput), parsedInput)
  }
  
  test("problem 1") {
    assertEquals(puzzle.problem1(parsedInput), 7)
  }
  
  test("problem 2") {
    assertEquals(puzzle.problem2(parsedInput), 19)
  }
}
