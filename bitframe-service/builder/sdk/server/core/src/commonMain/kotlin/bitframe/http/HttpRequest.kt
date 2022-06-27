package bitframe.http

import io.ktor.http.*

class HttpRequest(
    val method: HttpMethod,
    val path: String,
    val headers: Map<String, String>,
    val queryParameters: Map<String, String>,
    val body: String?
)