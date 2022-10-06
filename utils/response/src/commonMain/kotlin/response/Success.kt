package response

import response.payload.payloadOf
import io.ktor.http.*

data class Success<out D, out I>(
    override val status: Status,
    val payload: Payload<D, I>
) : Response<D, I>(status) {
    companion object {
        fun <D> of(data: D): Success<D, Nothing?> = Success(Status(HttpStatusCode.OK), payloadOf(data))
        fun <D, I> of(data: D, info: I): Success<D, I> = Success(Status(HttpStatusCode.OK), payloadOf(data, info))
        fun <D> of(status: Status, data: D): Success<D, Nothing?> = Success(status, payloadOf(data))
        fun <D> of(code: HttpStatusCode, data: D) = Success(Status(code), payloadOf(data))
        fun <D, I : Any> of(code: HttpStatusCode, data: D, info: I): Success<D, I> = Success(Status(code), payloadOf(data, info))
        fun <D, I> of(status: Status, data: D, info: I): Success<D, I> = Success(status, payloadOf(data, info))
    }
}