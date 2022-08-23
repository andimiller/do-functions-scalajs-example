import cats.effect.IO
import io.circe.Codec

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import io.circe.generic.semiauto._

import scala.concurrent.duration.DurationInt
import scala.scalajs.js

case class Input(name: Option[String])
object Input {
  implicit val codec: Codec[Input] = deriveCodec
}

case class Output(body: String)
object Output {
  implicit val codec: Codec[Output] = deriveCodec
}

@JSExportTopLevel("HelloWorld")
object HelloWorld {
  def effectfulProgram(input: Input): IO[Output] = {
    IO.println("ping") *>
      IO.sleep(1.second) *>
      IO.println("pong")
        .as(
          Output(
            s"Hello from effectful abstracted scala ${input.name.getOrElse("unknown name")}!"
          )
        )
  }

  def pureProgram(input: Input): Output =
    Output(
      s"Hello from pure abstracted scala ${input.name.getOrElse("unknown name")}!"
    )

  @JSExport
  def effectful(input: js.Any): js.Any =
    ServerlessFunction.mountIO(effectfulProgram).apply(input)

  @JSExport
  def pure(input: js.Any): js.Any =
    ServerlessFunction.mount(pureProgram).apply(input)
}
