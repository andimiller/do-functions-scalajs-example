import cats.effect.IO
import cats.effect.unsafe.implicits.global
import io.circe.{Decoder, Encoder}

import scala.scalajs.js
import io.circe.scalajs.{convertJsToJson, convertJsonToJs}
import io.circe.syntax.EncoderOps

object ServerlessFunction {

  def mount[I: Decoder, O: Encoder](f: I => O): js.Any => js.Any = { input: js.Any =>
    {
      for {
        js    <- convertJsToJson(input)
        input <- js.as[I]
        result = f(input).asJson
      } yield convertJsonToJs(result)
    }.toTry.get
  }

  def mountIO[I: Decoder, O: Encoder](f: I => IO[O]): js.Any => js.Any = { input: js.Any =>
    {
      for {
        js     <- IO.fromEither(convertJsToJson(input))
        input  <- IO.fromEither(js.as[I])
        result <- f(input)
      } yield convertJsonToJs(result.asJson)
    }.unsafeToPromise()
  }
}
