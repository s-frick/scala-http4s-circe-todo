package http4shelloworld.domain

import munit.FunSuite

import java.time.{LocalDateTime, ZoneId}
import http4shelloworld.domain.Todo


class TodoTestSuite extends FunSuite {
  test("todoDomain") {
    val actual = Todo("awesome todo", "high", LocalDateTime.of(2023, 3, 27, 22, 46))

    println(actual.toString)
    assertEquals(actual.toString, "Todo(awesome todo,high,2023-03-27T22:46)")
  }
}
