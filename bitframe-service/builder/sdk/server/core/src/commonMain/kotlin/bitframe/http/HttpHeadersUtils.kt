package bitframe.http

import io.ktor.http.headersOf as ktorHeadersOf

fun headersOf(vararg headers: Pair<String, String>) = ktorHeadersOf(
    *headers.map { (key, value) -> key to listOf(value) }.toTypedArray()
)