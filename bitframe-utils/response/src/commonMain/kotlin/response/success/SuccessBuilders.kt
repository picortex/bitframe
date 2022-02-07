package response.success

import response.Status
import response.Success
import io.ktor.http.*

fun <D> successOf(data: D) = Success.of(data)
fun <D, I : Any> successOf(data: D, info: I) = Success.of(data, info)
fun <D> successOf(status: Status, data: D) = Success.of(status, data)
fun <D> successOf(code: HttpStatusCode, data: D) = Success.of(code, data)
fun <D, I> successOf(status: Status, data: D, info: I) = Success.of(status, data, info)
fun <D, I : Any> successOf(code: HttpStatusCode, data: D, info: I) = Success.of(code, data, info)
