package bitframe.http

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import koncurrent.Later
import koncurrent.later.await
import response.Error
import response.Status
import response.responseOf

data class HttpRoute(
    val method: HttpMethod,
    val path: String,
    val handler: (HttpRequest) -> Later<HttpResponse>
) {
    fun info() = mapOf(
        "method" to method.value,
        "path" to path
    )

    suspend fun runHandlerCatching(request: HttpRequest): HttpResponse = try {
        handler(request).await()
    } catch (cause: Throwable) {
        cause.printStackTrace()
        println("Err (In HttpRoute: $this): ${cause.message}")
        responseOf(Status(HttpStatusCode.InternalServerError), cause, cause.message).toHttpResponse<Error>()
    }

    override fun toString() = "${method.value} $path"
}