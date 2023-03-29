ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

val Http4sVersion = "1.0.0-M29"
val MunitVersion = "0.7.29"
val LogbackVersion = "1.4.6"
val MunitCatsEffectVersion = "1.0.7"
val CatsVersion = "2.9.0"
val CirceVersion = "0.14.5"

val testDependencies = Seq(
  "org.scalameta" %% "munit" % MunitVersion % Test,
  "org.typelevel" %% "munit-cats-effect-3" % MunitCatsEffectVersion % Test,
)
val common = Seq(
  "org.typelevel" %% "cats-core" % CatsVersion
)

lazy val domain = (project in file("01-domain"))
  .settings(
    name := "domain",
    libraryDependencies ++= testDependencies ++ common,
  )

lazy val core = (project in file("02-core"))
  .dependsOn(domain)
  .settings(
    name := "core",
    libraryDependencies ++= testDependencies ++ common,
  )

lazy val inmemory = (project in file("03-persistence-inmemory"))
  .dependsOn(domain)
  .dependsOn(core)
  .settings(
    name := "persist-in-memory",
    libraryDependencies ++= testDependencies ++ common,
  )

lazy val `deliver-http` = (project in file("04-delivery-http4s-inMemoryDB"))
  .dependsOn(domain)
  .dependsOn(core)
  .dependsOn(inmemory)
  .settings(
    name := "delivery-http4s-inMemoryDB",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-ember-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
    ) ++ testDependencies ++ common,
  )

lazy val root = (project in file("."))
  .dependsOn(domain)
  .dependsOn(core)
  .dependsOn(inmemory)
  .dependsOn(`deliver-http`)
  .settings(
    name := "http4s-hello",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-ember-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
    ) ++ testDependencies ++ common,
    testFrameworks += new TestFramework("munit.Framework"),
  )
