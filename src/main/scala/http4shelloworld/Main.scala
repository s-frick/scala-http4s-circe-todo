package http4shelloworld

import cats.effect.{IO, IOApp}
import http4shelloworld.domain.events.DeliverHttpInMemory

object Main extends IOApp.Simple:
  val run = DeliverHttpInMemory.Program.run[IO]
