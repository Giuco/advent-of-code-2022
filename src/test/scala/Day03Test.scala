val test03Input =
  """vJrwpWtwJgWrhcsFMMfFFhFp
    |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
    |PmmdzqPrVvPwwTWBwg
    |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
    |ttgJtRGJQctTZtZT
    |CrZsJsPPZsGzwwsLwLmpwMDw""".stripMargin

val test03ParsedInput = List(
  Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp"),
  Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"),
  Rucksack("PmmdzqPrVvPwwTWBwg"),
  Rucksack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"),
  Rucksack("ttgJtRGJQctTZtZT"),
  Rucksack("CrZsJsPPZsGzwwsLwLmpwMDw")
)


class Day03Test extends munit.FunSuite {
  val puzzle = Day03()

  test("compartments are correctly defined") {
    val rucksack = Rucksack("abc123")
    assertEquals(rucksack.contents, Set('a', 'b', 'c', '1', '2', '3'))
    assertEquals(rucksack.compartment1, Set('a', 'b', 'c'))
    assertEquals(rucksack.compartment2, Set('1', '2', '3'))
  }

  test("raw input is parsed correctly") {
    val output = puzzle.parseInput(test03Input)
    assertEquals(output, test03ParsedInput)
    assert(
      output
        .zip(test03ParsedInput)
        .map((a, b) => a.compartment1 == b.compartment1 && a.compartment2 == b.compartment2)
        .forall(identity)
    )
  }

  test("test point intersection") {
    assertEquals(
      test03ParsedInput.map((a: Rucksack) => puzzle.pointIntersection(a.compartments)),
      List(16, 38, 42, 22, 20, 19),
    )
  }

  test("problem 1") {
    assertEquals(puzzle.problem1(test03ParsedInput), 157)
  }

  test("problem 2") {
    assertEquals(puzzle.problem2(test03ParsedInput), 70)
  }
}
