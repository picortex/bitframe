package bitframe.http.response

import bitframe.http.*
import bitframe.http.payload.payloadOf
import io.ktor.http.*

// Success
fun <D> responseOf(data: D): HttpResponse<D, Nothing> = HttpSuccess.Uninformed(HttpStatus(HttpStatusCode.OK), payloadOf(data))
fun <D, I : Any> responseOf(data: D, info: I): HttpResponse<D, I> = HttpSuccess.Informed(HttpStatus(HttpStatusCode.OK), HttpPayload.of(data, info))
fun <D> responseOf(status: HttpStatus, data: D): HttpResponse<D, Nothing> = HttpSuccess.Uninformed(status, payloadOf(data))
fun <D> responseOf(code: HttpStatusCode, data: D): HttpResponse<D, Nothing> = HttpSuccess.Uninformed(HttpStatus(code), payloadOf(data))
fun <D, I : Any> responseOf(code: HttpStatusCode, data: D, info: I): HttpResponse<D, I> = HttpSuccess.Informed(HttpStatus(code), payloadOf(data, info))
fun <D, I> responseOf(status: HttpStatus, data: D, info: I): HttpResponse<D, I> = HttpSuccess.Informed(status, HttpPayload.Informed(data, info))

// Failures
fun responseOf(cause: Throwable, message: String? = null): HttpResponse<Nothing, Nothing> = HttpFailure(
    status = HttpStatus(HttpStatusCode.BadRequest),
    error = HttpError(cause, message)
)