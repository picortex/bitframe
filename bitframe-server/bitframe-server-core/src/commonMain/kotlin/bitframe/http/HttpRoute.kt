package bitframe.http

import io.ktor.http.*

data class HttpRoute(
    val method: HttpMethod,
    val path: String,
    val handler: (HttpRequest) -> HttpResponse
)