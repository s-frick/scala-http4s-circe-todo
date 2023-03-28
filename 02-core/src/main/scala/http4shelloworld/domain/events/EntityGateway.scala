package http4shelloworld.domain.events

import http4shelloworld.domain.Todo

// interface to e.g. persistence
trait EntityGateway[F[_], TodoId] {
  def writeOne(todo: Todo): F[TodoId]

  def readOneById(id: TodoId): F[Option[Todo]]
  
  def readAll(): F[Seq[Todo]]
}
