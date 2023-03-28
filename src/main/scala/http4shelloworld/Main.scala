package http4shelloworld

import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple:
  val run = TodoServer.run[IO]
