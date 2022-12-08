class Day07Test extends munit.FunSuite {
  val rawInput =
    """$ cd /
      |$ ls
      |dir a
      |14848514 b.txt
      |8504156 c.dat
      |dir d
      |$ cd a
      |$ ls
      |dir e
      |29116 f
      |2557 g
      |62596 h.lst
      |$ cd e
      |$ ls
      |584 i
      |$ cd ..
      |$ cd ..
      |$ cd d
      |$ ls
      |4060174 j
      |8033020 d.log
      |5626152 d.ext
      |7214296 k""".stripMargin
  val parsedInput = List(
    Command(
      input = List("cd", "/"),
      output = null
    ),
    Command(
      input = List("ls"),
      output =
        """dir a
          |14848514 b.txt
          |8504156 c.dat
          |dir d""".stripMargin
    ),
    Command(
      input = List("cd", "a"),
      output = null
    ),
    Command(
      input = List("ls"),
      output =
        """dir e
          |29116 f
          |2557 g
          |62596 h.lst""".stripMargin
    ),
    Command(
      input = List("cd", "e"),
      output = null
    ),
    Command(
      input = List("ls"),
      output = "584 i"
    ),
    Command(
      input = List("cd", ".."),
      output = null
    ),
    Command(
      input = List("cd", ".."),
      output = null
    ),
    Command(
      input = List("cd", "d"),
      output = null
    ),
    Command(
      input = List("ls"),
      output =
        """4060174 j
          |8033020 d.log
          |5626152 d.ext
          |7214296 k""".stripMargin
    )
  )
  val puzzle = Day07()

  test("parse row") {
    assertEquals(
      puzzle.parseRow("$ cd a"),
      Command(List("cd", "a"), null)
    )

    assertEquals(
      puzzle.parseRow(
        """$ ls
          |dir e
          |29116 f
          |""".stripMargin),
      Command(List("ls"),
        """dir e
          |29116 f""".stripMargin)
    )
  }

  test("parse input") {
    assertEquals(
      puzzle.parseInput(rawInput),
      parsedInput
    )
  }


  test("get folder sizes") {
    val fileSystem = puzzle.buildFileSystem(parsedInput, List(), Map())
    val folderSizes = puzzle.getFolderSizes(fileSystem)
    val expectedFolderSizes = Map(
      List("/", "a") -> 94853,
      List("/", "d") -> 24933642,
      List("/", "a", "e") -> 584,
      List("/") -> 48381165,
    )

    assertEquals(folderSizes, expectedFolderSizes)
  }

  test("problem 1") {
    assertEquals(
      puzzle.problem1(parsedInput),
      95437
    )
  }

  test("problem 2") {
    assertEquals(
      puzzle.problem2(parsedInput),
      24933642
    )
  }
}
