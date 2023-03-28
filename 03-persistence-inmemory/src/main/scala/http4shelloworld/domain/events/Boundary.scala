package http4shelloworld.domain.events

import cats.{Applicative, Functor}
import http4shelloworld.domain.Todo


// UseCases
trait Boundary[F[_], TodoId] {
  def createOne(todo: Todo): F[TodoId]

  def readOneById(id: TodoId): F[Option[Todo]]
}

object Boundary {
  def dsl[F[_], TodoId](gateway: EntityGateway[F, TodoId]): Boundary[F, TodoId] =
    new Boundary[F, TodoId] {
      override def createOne(todo: Todo): F[TodoId] =
        gateway.writeOne(todo)

      override def readOneById(id: TodoId): F[Option[Todo]] =
        gateway.readOneById(id)
    }
}