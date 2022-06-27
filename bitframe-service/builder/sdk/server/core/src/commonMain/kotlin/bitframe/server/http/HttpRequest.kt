package bitframe.server.http

import io.ktor.http.*

@Deprecated("In favour of bitframe.http.HttpRequest")
class HttpRequest(
    val method: HttpMethod,
    val path: String,
    val headers: Map<String, String>,
    val queryParameters: Map<String, String>,
    val body: String?
)