package bitframe.http

import bitframe.http.payload.payloadOf
import io.ktor.http.*

data class HttpSuccess<out D, out I>(
    override val status: HttpStatus,
    val payload: HttpPayload<D, I>
) : HttpResponse<D, I>(status) {
    companion object {
        fun <D> of(data: D): HttpSuccess<D, Nothing?> = HttpSuccess(HttpStatus(HttpStatusCode.OK), payloadOf(data))
        fun <D, I> of(data: D, info: I): HttpSuccess<D, I> = HttpSuccess(HttpStatus(HttpStatusCode.OK), payloadOf(data, info))
        fun <D> of(status: HttpStatus, data: D): HttpSuccess<D, Nothing?> = HttpSuccess(status, payloadOf(data))
        fun <D> of(code: HttpStatusCode, data: D) = HttpSuccess(HttpStatus(code), payloadOf(data))
        fun <D, I : Any> of(code: HttpStatusCode, data: D, info: I): HttpSuccess<D, I> = HttpSuccess(HttpStatus(code), payloadOf(data, info))
        fun <D, I> of(status: HttpStatus, data: D, info: I): HttpSuccess<D, I> = HttpSuccess(status, payloadOf(data, info))
    }
}