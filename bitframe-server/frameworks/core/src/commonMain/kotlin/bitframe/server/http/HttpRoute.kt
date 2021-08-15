package bitframe.server.http

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

    override fun toString() = "${method.value} $path"
}