package http4shelloworld

import cats._
import cats.implicits._
import cats.effect.{Concurrent, Sync}

import http4shelloworld.domain.Todo
import http4shelloworld.domain.events.Boundary

import io.circe.generic.auto._
import io.circe.syntax._

import org.http4s._
import org.http4s.dsl._
import org.http4s.circe._
import org.http4s.implicits._


case class TodoId(id: Int)

object TodoRoutes:

  def todoRoutes[F[_]: Concurrent](T: Boundary[F, Int]): HttpRoutes[F] =
    val dsl = new Http4sDsl[F]{}
    import dsl._
    implicit val decoder: EntityDecoder[F, Todo] = jsonOf[F, Todo]
    HttpRoutes.of[F] {
      case GET -> Root / "todos" =>
        for {
          todo <- T.readAll()
          resp <- Ok(todo.asJson)
        } yield resp
        
      case GET -> Root / "todos" / IntVar(id) =>
        for {
          todo <- T.readOneById(id)
          resp <- Ok(todo.asJson)
        } yield resp

      case req @ POST -> Root / "todos" =>
        for {
          todo <- req.as[Todo]
          id <- T.createOne(todo)
          resp <- Ok(TodoId(id).asJson)
        } yield resp
    }
