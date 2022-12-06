package bitframe

import bitframe.http.HttpRequest
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import bitframe.http.toHttpResponse
import io.ktor.http.HttpStatusCode
import koncurrent.later.await
import response.Status
import response.responseOf

internal suspend fun HttpRoute.runHandlerCatching(request: HttpRequest): HttpResponse = try {
    println("Handling ${request.method}: ${request.path}")
    println(request.body)
    handler(request).await()
} catch (cause: Throwable) {
    cause.printStackTrace()
    println("Err (In HttpRoute: $this): ${cause.message}")
    responseOf(Status(HttpStatusCode.InternalServerError), cause, cause.message).toHttpResponse<Error>()
}