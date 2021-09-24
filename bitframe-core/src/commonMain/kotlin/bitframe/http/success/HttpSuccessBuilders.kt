package bitframe.http.success

import bitframe.http.HttpPayload
import bitframe.http.HttpStatus
import bitframe.http.HttpSuccess
import bitframe.http.payload.payloadOf
import io.ktor.http.*

fun <D> successOf(data: D) = HttpSuccess.Uninformed(HttpStatus(HttpStatusCode.OK), payloadOf(data))
fun <D, I : Any> successOf(data: D, info: I) = HttpSuccess.Informed(HttpStatus(HttpStatusCode.OK), HttpPayload.of(data, info))
fun <D> successOf(status: HttpStatus, data: D) = HttpSuccess.Uninformed(status, payloadOf(data))
fun <D> successOf(code: HttpStatusCode, data: D) = HttpSuccess.Uninformed(HttpStatus(code), payloadOf(data))
fun <D, I : Any> successOf(code: HttpStatusCode, data: D, info: I) = HttpSuccess.Informed(HttpStatus(code), payloadOf(data, info))
fun <D, I> successOf(status: HttpStatus, data: D, info: I) = HttpSuccess.Informed(status, HttpPayload.Informed(data, info))