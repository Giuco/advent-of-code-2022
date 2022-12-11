case class Pos(i: Int, j: Int) {}

case class Mov(direction: Char, distance: Int) {}

class Day09 extends Puzzle[List[Mov], Int] {
  val puzzleId = "09"

  override def parseInput(input: String): List[Mov] =
    input
      .split("\n")
      .map { s =>
        val Array(direction, count) = s.split(" ")
        Mov(direction.charAt(0), count.toInt)
      }
      .toList

  def singleMove(direction: Char, positions: List[Pos]): List[Pos] =
    var prevKnot = move(positions.head, direction)
    var currKnot: Pos = null

    prevKnot +: (for
      knot <- positions.drop(1)
    yield
      currKnot = follow(knot, prevKnot)
      prevKnot = currKnot
      currKnot)

  def moveKnotsGeneral(movements: List[Mov], nKnots: Int): List[List[Pos]] =
    def inner(movements: List[Mov],
              positions: List[Pos],
              previousPositions: List[List[Pos]]): List[List[Pos]] =
      if (movements.isEmpty) return previousPositions

      // TODO: Change this to a reduce
      var newPositions = positions
      val newHistoryPositions = for
        _ <- 0 until movements.head.distance
      yield
        newPositions = singleMove(movements.head.direction, newPositions)
        newPositions

      inner(
        movements.drop(1),
        newHistoryPositions.last,
        previousPositions ++ newHistoryPositions
      )

    inner(
      movements,
      List.fill(nKnots)(Pos(0, 0)),
      List[List[Pos]](),
    )

  def move(pos: Pos, direction: Char): Pos =
    direction match
      case 'L' => Pos(pos.i, pos.j - 1)
      case 'R' => Pos(pos.i, pos.j + 1)
      case 'U' => Pos(pos.i + 1, pos.j)
      case 'D' => Pos(pos.i - 1, pos.j)

  def follow(tail: Pos, head: Pos): Pos =
    val iDist = head.i - tail.i
    val jDist = head.j - tail.j

    if iDist.abs > 1 || jDist.abs > 1 then (iDist.sign, jDist.sign) match
      case (i, 0) => Pos(tail.i + i, tail.j)
      case (0, j) => Pos(tail.i, tail.j + j)
      case (i, j) => Pos(tail.i + i, tail.j + j)
    else tail

  def countTailPositions(input: List[Mov], nKnots: Int): Int =
    val posHistory = moveKnotsGeneral(input, nKnots)
    posHistory.map(_.last).distinct.size

  override def problem1(input: List[Mov]): Int =
    countTailPositions(input, 2)

  override def problem2(input: List[Mov]): Int =
    countTailPositions(input, 10)

}


object Day09 extends App {
  Day09().printSolutions()
}