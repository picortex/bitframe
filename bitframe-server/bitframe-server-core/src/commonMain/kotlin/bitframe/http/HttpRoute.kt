package bitframe.http

import io.ktor.http.*

data class HttpRoute(
    val method: HttpMethod,
    val path: String,
    val handler: (HttpRequest) -> HttpResponse
) {
    fun info() = mapOf(
        "method" to method.value,
        "path" to path
    )
}