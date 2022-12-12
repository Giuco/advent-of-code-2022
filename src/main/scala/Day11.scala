case class MonkeyTest(divisibleBy: BigInt, monkeyTrue: Int, monkeyFalse: Int) {
  def monkeyToThrow(value: BigInt): Int = if (value % divisibleBy) == 0 then monkeyTrue else monkeyFalse
}

object MonkeyTest {
  def parse(rawInput: String): MonkeyTest =
    val Array(rawDivisible, rawTrue, rawFalse) = rawInput.split("\n").map(_.split(":").last.strip())

    MonkeyTest(
      rawDivisible.split(" ").last.toInt,
      rawTrue.split(" ").last.toInt,
      rawFalse.split(" ").last.toInt
    )
}

case class Monkey(id: Int, items: List[BigInt], operation: String, test: MonkeyTest) {
  def runOperation(item: BigInt): BigInt =
    val substitutedOperation = operation.replace("old", item.toString)
    substitutedOperation.split(" ") match
      case Array(a, "+", b) => a.toInt + b.toInt
      case Array(a, "*", b) => a.toInt * b.toInt

  def getNewLevels: List[BigInt] = items.map(runOperation).map(x => (x / 3) % 96577)
}


object Monkey {
  def parse(rawInput: String): Monkey =
    val Array(rawMonkeyId, rawItems, rawOperation, rawTest) = rawInput.split("\n", 4).map(_.strip())

    val monkeyIdPattern = "([0-9]+)".r.unanchored
    val monkeyId = rawMonkeyId match
      case monkeyIdPattern(d) => d.toInt

    val items: List[BigInt] = rawItems.split(": ").last.split(", ").map(BigInt(_)).toList
    val operation: String = rawOperation.split("=").last.strip()
    val monkeyTest = MonkeyTest.parse(rawTest)

    Monkey(monkeyId, items, operation, monkeyTest)
}

class Day11 extends Puzzle[List[Monkey], BigInt, BigInt] {
  val puzzleId = "11"

  override def parseInput(input: String): List[Monkey] =
    input.split("\n\n").map((s: String) => Monkey.parse(s.strip())).toList

  def runMonkeyTurn(monkeys: List[Monkey], i: Int): (List[Monkey], BigInt) =
    val currentMonkey = monkeys(i)
    val moves = currentMonkey
      .getNewLevels
      .map(i => (currentMonkey.test.monkeyToThrow(i), i))

    val changedMonkeys = moves
      .groupMap(_._1)(_._2)
      .toList
      .map { (monkeyId, items) =>
        val monkey = monkeys(monkeyId)
        monkey.copy(items = monkey.items ++ items)
      }

    val newMonkeyState = changedMonkeys
      .foldLeft(monkeys)((allMonkeys, changedMonkey) => allMonkeys.updated(changedMonkey.id, changedMonkey))
      .updated(i, currentMonkey.copy(items = List()))

    (newMonkeyState, moves.size)

  def runRound(monkeys: List[Monkey], inspectCount: List[BigInt]): (List[Monkey], List[BigInt]) =
    monkeys
      .foldLeft((monkeys, inspectCount)) { (monkeysAndInspection, monkeyOnTurn) =>
        val monkeyId = monkeyOnTurn.id
        val currentMonkeyState = monkeysAndInspection._1
        val currentInspectCount = monkeysAndInspection._2
        val (newMonkeyState, nInspections) = runMonkeyTurn(currentMonkeyState, monkeyId)
        val newInspectCount = currentInspectCount.updated(monkeyId, currentInspectCount(monkeyId) + nInspections)
        
        (newMonkeyState, newInspectCount)
      }

  def runNRounds(monkeys: List[Monkey], nRounds: Int): (List[Monkey], List[BigInt]) =
    (0 until nRounds)
      .foldLeft((monkeys, List.fill(monkeys.size)(BigInt(0)))) { (monkeysAndInspection, i) =>
        val currentMonkeyState = monkeysAndInspection._1
        val currentInspectCount = monkeysAndInspection._2
        runRound(currentMonkeyState, currentInspectCount)
      }

  override def problem1(input: List[Monkey]): BigInt =
    val (finalMonkeyState, inspectionCount) = runNRounds(input, 20)
    inspectionCount.sorted.takeRight(2).product

  override def problem2(input: List[Monkey]): BigInt =
    val (finalMonkeyState, inspectionCount) = runNRounds(input, 10000)
    inspectionCount.sorted.takeRight(2).product

}

object Day11 extends App {
  Day11().printSolutions()
}