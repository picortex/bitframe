package bitframe.server.http

import bitframe.response.Error
import bitframe.response.Status
import bitframe.response.response.responseOf
import io.ktor.http.*

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
        val res = handler(request)
        println("Response went through")
        res
    } catch (cause: Throwable) {
        cause.printStackTrace()
        println("Err (In Application): ${cause.message}")
        responseOf(Status(HttpStatusCode.InternalServerError), cause, cause.message).toHttpResponse<Error>()
    }

    override fun toString() = "${method.value} $path"
}