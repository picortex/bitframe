package bitframe.http.success

import bitframe.http.HttpStatus
import bitframe.http.HttpSuccess
import io.ktor.http.*

fun <D> successOf(data: D) = HttpSuccess.of(data)
fun <D, I : Any> successOf(data: D, info: I) = HttpSuccess.of(data, info)
fun <D> successOf(status: HttpStatus, data: D) = HttpSuccess.of(status, data)
fun <D> successOf(code: HttpStatusCode, data: D) = HttpSuccess.of(code, data)
fun <D, I> successOf(status: HttpStatus, data: D, info: I) = HttpSuccess.of(status, data, info)
fun <D, I : Any> successOf(code: HttpStatusCode, data: D, info: I) = HttpSuccess.of(code, data, info)
