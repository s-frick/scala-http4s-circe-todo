package http4shelloworld.domain.events

import cats.*
import cats.syntax.all.*
import http4shelloworld.domain._

import java.time.LocalDateTime
import scala.collection.mutable

object InMemoryEntityGateway {
  var todos: Vector[Todo] = Vector(Todo("awesome todo", "high", LocalDateTime.of(2023, 3, 27, 22, 46)))

  def dsl[F[_]](implicit a: Applicative[F]): EntityGateway[F, Int] = {
    new EntityGateway[F, Int] {
      
      override def writeOne(todo: Todo): F[Int] =
        if (todos.isEmpty) todos = Vector(todo)
        else todos = todos :+ todo
        a.pure(todos.indexOf(todo))

      override def readOneById(id: Int): F[Option[Todo]] =
        a.pure(
          if (todos.sizeCompare(id) >= 0) Some(todos(id))
          else None
        )

      override def readAll(): F[Seq[Todo]] =
        a.pure(
          todos
        )
        
        
    }
  }
}