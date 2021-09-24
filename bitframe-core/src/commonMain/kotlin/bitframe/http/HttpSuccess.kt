package bitframe.http

import bitframe.http.payload.payloadOf
import io.ktor.http.*

sealed class HttpSuccess<out D, out I>(
    override val status: HttpStatus,
    open val payload: HttpPayload<D, I>
) : HttpResponse<D, I>(status) {
    data class Informed<out D, out I>(
        override val status: HttpStatus,
        override val payload: HttpPayload.Informed<D, I>
    ) : HttpSuccess<D, I>(status, payload)

    data class Uninformed<out D>(
        override val status: HttpStatus,
        override val payload: HttpPayload.Uniformed<D>
    ) : HttpSuccess<D, Nothing?>(status, payload)

    companion object {
        fun <D> of(data: D) = Uninformed(HttpStatus(HttpStatusCode.OK), HttpPayload.Uniformed(data))
        fun <D, I : Any> of(data: D, info: I) = Informed(HttpStatus(HttpStatusCode.OK), HttpPayload.of(data, info))
        fun <D> of(status: HttpStatus, data: D) = Uninformed(status, HttpPayload.Uniformed(data))
        fun <D> of(code: HttpStatusCode, data: D) = Uninformed(HttpStatus(code), HttpPayload.Uniformed(data))
        fun <D, I : Any> of(code: HttpStatusCode, data: D, info: I) = Informed(HttpStatus(code), payloadOf(data, info))
        fun <D, I> of(status: HttpStatus, data: D, info: I) = Informed(status, HttpPayload.Informed(data, info))
    }
}