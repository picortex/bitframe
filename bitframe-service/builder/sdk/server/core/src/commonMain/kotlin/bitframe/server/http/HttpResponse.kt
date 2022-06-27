package bitframe.server.http

import io.ktor.http.*

@Deprecated("In favour of bitframe.http.HttpResponse")
data class HttpResponse(
    val status: HttpStatusCode,
    val body: String = ""
)