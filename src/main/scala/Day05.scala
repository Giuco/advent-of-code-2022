import scala.collection.mutable


case class Game(stacks: List[List[Char]], ops: List[Movement]) {}

case class Movement(qtd: Int, from: Int, to: Int) {}

class Day05 extends Puzzle[Game, String, String] {
  val puzzleId = "05"

  def parseMovements(input: String): List[Movement] =
    val pattern = "move\\s(?<qtd>\\d+)\\sfrom\\s(?<from>\\d+)\\sto\\s(?<to>\\d+)".r
    val movements = for rawMovement <- input.split("\n")
                        pattern(qtd, from, to) = rawMovement
    yield Movement(qtd.toInt, from.toInt, to.toInt)

    movements.toList

  def parseStacks(input: String): List[List[Char]] =
    val rawStacks: List[String] = input.split("\n").dropRight(1).toList
    val stackMatrix: List[List[Char]] = rawStacks
      .map(_.drop(1).grouped(4).map(_.head).toList)
      .transpose
    val stacks = stackMatrix
      .map((s: List[Char]) => s.filter(_ != ' '))

    stacks
  
  override def parseInput(input: String): Game =
    val rawParts = input.split("\n\n")
    val rawStacks = rawParts(0)
    val rawMovements = rawParts(1)

    Game(parseStacks(rawStacks), parseMovements(rawMovements))

  def doMove(stacks: List[List[Char]], movement: Movement, doubleMov: Boolean): List[List[Char]] =
    val toMove = stacks(movement.from - 1).take(movement.qtd)
    val toMoveAdjusted: List[Char] = if(doubleMov) toMove else toMove.reverse
    val newFrom = stacks(movement.from - 1).drop(movement.qtd)
    val newTo = toMoveAdjusted ++ stacks(movement.to - 1)
    
    stacks
      .updated(movement.from-1, newFrom)
      .updated(movement.to-1, newTo)
  
  override def problem1(input: Game): String =
    val finalStacks = input.ops
      .foldLeft(input.stacks)((s: List[List[Char]], m: Movement) => doMove(s, m, false))
    
    finalStacks.map(_.head).mkString("")

  override def problem2(input: Game): String =
    val finalStacks = input.ops
      .foldLeft(input.stacks)((s: List[List[Char]], m: Movement) => doMove(s, m, true))

    finalStacks.map(_.head).mkString("")
}

object Day05 extends App {
  Day05().printSolutions()
}