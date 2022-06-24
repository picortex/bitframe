package bitframe.server.http

import io.ktor.http.*
import response.Error
import response.Status
import response.responseOf

@Deprecated("In favour of bitframe.http.HttpRoute")
data class HttpRoute(
    val method: HttpMethod,
    val path: String,
    val handler: suspend (HttpRequest) -> HttpResponse
) {
    fun info() = mapOf(
        "method" to method.value,
        "path" to path
    )

    suspend fun runHandlerCatching(request: HttpRequest): HttpResponse = try {
        handler(request)
    } catch (cause: Throwable) {
        cause.printStackTrace()
        println("Err (In HttpRoute: $this): ${cause.message}")
        responseOf(Status(HttpStatusCode.InternalServerError), cause, cause.message).toHttpResponse<Error>()
    }

    override fun toString() = "${method.value} $path"
}