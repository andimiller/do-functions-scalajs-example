# do-functions-scalajs-example

An example project for deploying scala.js functions to DigitalOcean's Functions (their serverless thing).

Includes a thin wrapper that can coerce types around and let you publish pure and effectful functions.

## Full deployment Config

1. `doctl serverless init hello-scala`
2. `sbt fullOptJS`
3. `cp target/scala-2.13/do-functions-scalajs-example-opt/main.js hello-scala/packages/sample/hello/hello.js`
4. edit `hello-scala/project.yml` and set `main` to either `HelloWorld.pure` or `HelloWorld.effectful`
5. `doctl serverless deploy hello-scala`