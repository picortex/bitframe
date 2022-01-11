# Controllers

It is idiomatic if a controller would take a [HttpRequest](../../bitframe-server/frameworks/core/src/commonMain/kotlin/bitframe/server/http/HttpRequest.kt)
And return a [HttpResponse](../../bitframe-server/frameworks/core/src/commonMain/kotlin/bitframe/server/http/HttpResponse.kt)
converting anything and everything with in it

## The Role of a controller

- decoding inputs (i.e. request body or query params) to internal models
- validating those inputs so that they can be passed on to the next layer
- encode the response to a well json output