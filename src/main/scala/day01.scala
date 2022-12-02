def parseInput(input: String): List[List[Int]] =
  input
    .split("\n\n")
    .map(_.split("\n"))
    .map(_.map(_.strip().toInt).toList)
    .toList

def getTopNCalories(calories: List[List[Int]], n: Int): List[Int] =
  calories.map(_.sum).sorted.takeRight(n)

def solve01(input: String): Int =
  parseInput.andThen(getTopNCalories(_, n=1)).andThen(_.sum)(input)

def solve02(input: String): Int =
  parseInput.andThen(getTopNCalories(_, n=3)).andThen(_.sum)(input)


object day01 extends App {
  val input = scala.io.Source.fromFile("data/day01/1.txt").mkString
  println(s"1: ${solve01(input)}")
  println(s"2: ${solve02(input)}")
}
