package bitframe.server.http

import io.ktor.http.headersOf as ktorHeadersOf
@Deprecated("In favour of bitframe.http.headersOf")
fun headersOf(vararg headers: Pair<String, String>) = ktorHeadersOf(
    *headers.map { (key, value) -> key to listOf(value) }.toTypedArray()
)