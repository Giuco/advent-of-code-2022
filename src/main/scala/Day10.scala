enum InstructionType:
  case NOOP, ADDX

case class Instruction(t: InstructionType, q: Option[Int]) {}

class Day10 extends Puzzle[List[Instruction], Int, String] {
  val puzzleId = "10"

  def parseRow(row: String): Instruction =
    val parts = row.split(" ")
    val t = InstructionType.valueOf(parts.head.toUpperCase())
    val q = if parts.size > 1 then Some(parts.last.toInt) else None
    Instruction(t, q)

  override def parseInput(input: String): List[Instruction] =
    input.split("\n").map(parseRow).toList
    
  def runCycles(instructions: List[Instruction], registerHistory: List[Int]): List[Int] =
    if instructions.isEmpty then return registerHistory
    
    val lastRegister = registerHistory.lastOption.getOrElse(1)
    
    val newRegisterHistory = instructions.head match
      case Instruction(InstructionType.NOOP, _) => registerHistory :+ lastRegister
      case Instruction(InstructionType.ADDX, v) => registerHistory :+ lastRegister :+ (lastRegister + v.get)
      
    runCycles(instructions.tail, newRegisterHistory)
    
    
  def drawCRT(registerHistory: List[Int]): List[List[Char]] =
    val pixels = '#' +: (for 
      a <- registerHistory.tail.zipWithIndex
    yield
      val register = a._1 
      val pixel = a._2 + 1
      val column = pixel % 40
      val lit = if (register - column).abs <= 1 then '#' else '.'
      lit)
    
    pixels.dropRight(1).grouped(40).toList

  override def problem1(input: List[Instruction]): Int =
    val registerHistory = runCycles(input, List(1))
    println(registerHistory)
    println(registerHistory.size)
    val relevantCycles = List(20, 60, 100, 140, 180, 220)
    registerHistory
      .zipWithIndex
      .filter((v, i) => relevantCycles.contains(i+1))
      .map((v, i) => v * (i+1))
      .sum

  override def problem2(input: List[Instruction]): String =
    val registerHistory = runCycles(input, List(1))
    println(registerHistory)
    val pixels = drawCRT(registerHistory)
    val stringRepresentation = pixels.map(_.mkString).mkString("\n")
    stringRepresentation
}

object Day10 extends App {
  Day10().printSolutions()
}
