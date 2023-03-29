package http4shelloworld.domain.events

import cats.effect.Async
import cats.syntax.all.*
import com.comcast.ip4s.*
import http4shelloworld.TodoRoutes
import http4shelloworld.domain.events.{Boundary, InMemoryEntityGateway}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.middleware.Logger


object DeliverHttpInMemory:
  object Program:
    def run[F[_]: Async]: F[Nothing] = {
      val todoBoundary: Boundary[F, Int] = Boundary.dsl(InMemoryEntityGateway.dsl)

      
      val httpApp = (
        TodoRoutes.todoRoutes[F](todoBoundary)
        ).orNotFound

      val finalHttpApp = Logger.httpApp(false, false)(httpApp)

      EmberServerBuilder.default[F]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8080")
        .withHttpApp(finalHttpApp)
        .build
    }.useForever
