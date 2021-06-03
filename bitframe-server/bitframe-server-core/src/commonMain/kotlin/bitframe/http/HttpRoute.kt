package bitframe.http

import io.ktor.http.*

open class HttpRoute(
    val method: HttpMethod,
    val path: String,
    val handler: (HttpRequest) -> HttpResponse
)