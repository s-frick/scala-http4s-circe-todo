package http4shelloworld

import java.time.LocalDateTime

package object domain {
  case class Todo(
                   //                   id: Todo.TodoId,
                   description: String,
                   priority: String,
                   dueDate: LocalDateTime
                 )

  object Todo {
//    case class TodoId(value: String)
  }


//  case object High extends Priority("high")
//
//  case object Medium extends Priority("medium")
//
//  case object Low extends Priority("low")
}
