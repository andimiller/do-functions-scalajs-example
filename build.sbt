ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "do-functions-scalajs-example",
    libraryDependencies ++= List(
      "org.typelevel" %%% "cats-effect-std" % "3.3.14",
      "org.typelevel" %%% "cats-effect" % "3.3.14",
      "io.circe" %%% "circe-parser" % "0.14.2",
      "io.circe" %%% "circe-generic" % "0.14.2"
    )
  )
