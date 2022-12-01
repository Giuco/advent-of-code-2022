class day01 extends munit.FunSuite {
  test("test1") {
    val result: Int = sumTwo(2, 2)
    assertEquals(result, 4)
  }

  test("test2") {
    val result: Int = sumTwo(3, 5)
    assertEquals(result, 2)
  }
}
