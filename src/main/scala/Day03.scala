case class Rucksack(rawContents: String) {
  val contents: Set[Char] = rawContents.toSet

  val compartment1: Set[Char] = rawContents.take(rawContents.length / 2).toSet

  val compartment2: Set[Char] = rawContents.slice(rawContents.length / 2, rawContents.length).toSet

  val compartments: List[Set[Char]] = List(compartment1, compartment2)
}


class Day03 extends Puzzle[List[Rucksack], Int] {
  override val puzzleId: String = "03"

  override def parseInput(input: String): List[Rucksack] =
    input.split("\n").map(Rucksack.apply).toList

  def pointIntersection(rucks: Iterable[Set[Char]]): Int =
    rucks
      .reduce(_.intersect(_))
      .map((a: Char) => if (a.isUpper) a.toInt - 38 else a.toInt - 96)
      .sum

  override def problem1(input: List[Rucksack]): Int =
    input.map((a: Rucksack) => pointIntersection(a.compartments)).sum

  override def problem2(input: List[Rucksack]): Int =
    input
      .grouped(3)
      .flatMap(a => a(0).contents.intersect(a(1).contents).intersect(a(2).contents))
      .map((a: Char) => if (a.isUpper) a.toInt - 38 else a.toInt - 96)
      .sum
}

object Day03 extends App {
  val puzzle = Day03()
  puzzle.printSolutions()
}