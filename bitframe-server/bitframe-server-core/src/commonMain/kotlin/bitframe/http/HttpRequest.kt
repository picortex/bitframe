package bitframe.http

import io.ktor.http.*

class HttpRequest(
    val method: HttpMethod,
    val path: String,
    val headers: Map<HttpHeaders, String>,
    val body: String?
)