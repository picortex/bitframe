package bitframe.server.http

import response.Error
import response.Status
import response.response.responseOf
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
        println("Processing request")
        val res: HttpResponse = handler(request)
        println("Response went through")
        res
    } catch (cause: Throwable) {
        cause.printStackTrace()
        println("Err (In HttpRoute): ${cause.message}")
        responseOf(Status(HttpStatusCode.InternalServerError), cause, cause.message).toHttpResponse<Error>()
    }

    override fun toString() = "${method.value} $path"
}