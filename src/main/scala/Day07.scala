type FileSystem = Map[List[String], Int]

case class Command(input: List[String], output: String) {}

class Day07 extends Puzzle[List[Command], Int, Int] {
  val puzzleId = "07"

  override def parseInput(input: String): List[Command] =
    input.split("\n\\$").map(parseRow).toList

  def parseRow(rawCommand: String): Command =
    val io = rawCommand.split("\n", 2)
    val i = io(0).replace("$", "").strip().split(" ").toList
    val o = if (io.length == 1) null else io(1).strip()
    Command(i, o)

  def addToFileSytem(fileSystem: FileSystem, lsOutput: String, pwd: List[String]): FileSystem =
    val parsedLs = lsOutput
      .split("\n")
      .filter(!_.startsWith("dir"))
      .map(_.split(" "))
      .map(f => (pwd :+ f(1), f(0).toInt))
      .toMap

    fileSystem ++ parsedLs

  def buildFileSystem(commands: List[Command], pwd: List[String], fileSystem: FileSystem): FileSystem =
    if (commands.isEmpty) fileSystem else commands.head match
      case Command(List("cd", ".."), _) => buildFileSystem(commands.drop(1), pwd.dropRight(1), fileSystem)
      case Command(List("cd", folder), _) => buildFileSystem(commands.drop(1), pwd.appended(folder), fileSystem)
      case Command(List("ls"), lsOutput) => buildFileSystem(commands.drop(1), pwd, addToFileSytem(fileSystem, lsOutput, pwd))

  def getFolderSizes(fileSystem: FileSystem): FileSystem =
    fileSystem
      .toList
      .flatMap((k, v) => (1 until k.length).map(i => (k.take(i), v)))
      .groupMapReduce(_._1)(_._2)(_ + _)

  override def problem1(input: List[Command]): Int =
    val fileSystem = buildFileSystem(input, List(), Map())
    val folderSizes = getFolderSizes(fileSystem)
    folderSizes
      .values
      .filter(_ <= 100000)
      .sum

  override def problem2(input: List[Command]): Int =
    val fileSystem = buildFileSystem(input, List(), Map())
    val folderSizes = getFolderSizes(fileSystem)
    val spaceToFree = -(40000000 - fileSystem.values.sum)
    folderSizes
      .values
      .filter(_ >= spaceToFree)
      .min
}

object Day07 extends App {
  Day07().printSolutions()
}