package bitframe.server.http

import io.ktor.http.*

data class HttpResponse(
    val status: HttpStatusCode,
    val body: String = ""
)