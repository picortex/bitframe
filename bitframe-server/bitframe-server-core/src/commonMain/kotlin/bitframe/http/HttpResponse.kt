package bitframe.http

import io.ktor.http.*

class HttpResponse(
    val status: HttpStatusCode,
    val body: String? = null
)