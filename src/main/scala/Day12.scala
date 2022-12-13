case class Point(x: Int, y: Int) {}

type Matrix[A] = List[List[A]]


class Day12 extends Puzzle[Matrix[Char], Int, Int] {
  val puzzleId = "12"

  override def parseInput(input: String): Matrix[Char] =
    input
      .split("\n")
      .map(_.toCharArray.toList)
      .toList

  def findPointByValue[A](matrix: Matrix[A], value: A): Point =
    val initialPointI = matrix.indexWhere(_.contains(value))
    val initialPointJ = matrix(initialPointI).indexWhere(_ == value)
    Point(initialPointI, initialPointJ)

  def findPossiblePaths(matrix: Matrix[Char]): Int =
    val initialPoint = findPointByValue(matrix, 'S')
    val endPoint = findPointByValue(matrix, 'E')
    
    val newMatrix1 = matrix
      .updated(initialPoint.x, matrix(initialPoint.x).updated(initialPoint.y, 'a'))
    val newMatrix2 = newMatrix1
      .updated(endPoint.x, newMatrix1(endPoint.x).updated(endPoint.y, 'z'))
    
    val dp: Matrix[Int] = List.fill(matrix.size)(List.fill(matrix.head.size)(-1))
    
    val finalDp = findPossiblePaths(
      newMatrix2, 
      endPoint, 
      List((initialPoint, 0)),
      dp.updated(initialPoint.x, dp(initialPoint.x).updated(initialPoint.y, 0)),
    )
    println(endPoint)
    println(finalDp.map(row => row.map("%03d".format(_))).mkString("\n"))
    
    finalDp(endPoint.x)(endPoint.y)


  def isTraversable(matrix: Matrix[Char], from: Point, to: Point): Boolean =
    if to.x < 0 || to.y < 0 then false
    else if to.x >= matrix.size || to.y >= matrix.head.size then false
    else if (matrix(from.x)(from.y).toInt - matrix(to.x)(to.y).toInt).abs > 1 then false
//    else if (matrix(from.x)(from.y).toInt - matrix(to.x)(to.y).toInt).sign == 1 then false
    else true

  def findPossiblePaths(matrix: Matrix[Char],
                        endPoint: Point,
                        pointQueue: List[(Point, Int)],
                        dp: Matrix[Int]): Matrix[Int] =
    if pointQueue.isEmpty then return dp
    
    val (currentPoint, currentDistance) = pointQueue.head
    
    // Getting possibles paths to go forward
    val pathsToGo = List((-1, 0), (0, -1), (0, 1), (1, 0))
      .map((x, y) => Point(currentPoint.x + x, currentPoint.y + y))
      .filter(isTraversable(matrix, currentPoint, _))
      .map((_, currentDistance+1))
      
    
    // If we are already at the end point, we shouldn't add more new future points to the queue
    val newDP = dp
      .updated(currentPoint.x, dp(currentPoint.x).updated(currentPoint.y, currentDistance))
    
    val newPointQueue = (if currentPoint == endPoint
    then pointQueue.tail
    else pointQueue.tail ++ pathsToGo).filter((p, i) => dp(p.x)(p.y) == -1)


    findPossiblePaths(
      matrix,
      endPoint,
      newPointQueue,
      newDP
    )


  override def problem1(input: Matrix[Char]): Int =
    findPossiblePaths(input)

  override def problem2(input: Matrix[Char]): Int = 0
}


object Day12 extends App {
  Day12().printSolutions()
}