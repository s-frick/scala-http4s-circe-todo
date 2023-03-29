package http4shelloworld

import java.time.LocalDateTime

package object domain {
  case class Todo(
                   description: String,
                   priority: String,
                   dueDate: LocalDateTime
                 )

  object Todo {
//    case class TodoId(value: String)
  }
}
