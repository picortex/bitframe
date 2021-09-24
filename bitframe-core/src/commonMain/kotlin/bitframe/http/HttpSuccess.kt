package bitframe.http

import bitframe.http.payload.payloadOf
import io.ktor.http.*

sealed class HttpSuccess<out D, out I>(
    override val status: HttpStatus
) : HttpResponse<D, I>(status) {
    data class Informed<out D, out I>(
        override val status: HttpStatus,
        val payload: HttpPayload.Informed<D, I>
    ) : HttpSuccess<D, I>(status)

    data class Uninformed<out D>(
        override val status: HttpStatus,
        val payload: HttpPayload<D, Nothing>
    ) : HttpSuccess<D, Nothing>(status)

    companion object {
        fun <D> of(data: D) = Uninformed(HttpStatus(HttpStatusCode.OK), payloadOf(data))
        fun <D, I : Any> of(data: D, info: I) = Informed(HttpStatus(HttpStatusCode.OK), HttpPayload.of(data, info))
        fun <D> of(status: HttpStatus, data: D) = Uninformed(status, payloadOf(data))
        fun <D> of(code: HttpStatusCode, data: D) = Uninformed(HttpStatus(code), payloadOf(data))
        fun <D, I : Any> of(code: HttpStatusCode, data: D, info: I) = Informed(HttpStatus(code), payloadOf(data, info))
        fun <D, I> of(status: HttpStatus, data: D, info: I) = Informed(status, HttpPayload.Informed(data, info))
    }

    val payLoad
        get() = when (this) {
            is Informed -> payload
            is Uninformed -> payload
        }
}