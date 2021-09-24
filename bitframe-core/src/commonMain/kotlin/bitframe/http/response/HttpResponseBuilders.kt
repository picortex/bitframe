package bitframe.http.response

import bitframe.http.*
import bitframe.http.payload.payloadOf
import io.ktor.http.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.experimental.ExperimentalTypeInference

// Success
fun <D> responseOf(data: D): HttpResponse<D, Nothing?> = HttpSuccess(HttpStatus(HttpStatusCode.OK), payloadOf(data))
fun <D, I : Any> responseOf(data: D, info: I): HttpResponse<D, I> = HttpSuccess(HttpStatus(HttpStatusCode.OK), HttpPayload.of(data, info))
fun <D> responseOf(status: HttpStatus, data: D): HttpResponse<D, Nothing?> = HttpSuccess(status, payloadOf(data))
fun <D> responseOf(code: HttpStatusCode, data: D): HttpResponse<D, Nothing?> = HttpSuccess(HttpStatus(code), payloadOf(data))
fun <D, I : Any> responseOf(code: HttpStatusCode, data: D, info: I): HttpResponse<D, I> = HttpSuccess(HttpStatus(code), payloadOf(data, info))
fun <D, I> responseOf(status: HttpStatus, data: D, info: I): HttpResponse<D, I> = HttpSuccess(status, HttpPayload(data, info))

// Failures
fun responseOf(
    cause: Throwable,
    message: String? = null
): HttpResponse<Nothing, Nothing> = responseOf(
    status = HttpStatus(HttpStatusCode.BadRequest),
    cause = cause,
    message = message
)

fun responseOf(
    status: HttpStatus,
    cause: Throwable,
    message: String? = null
): HttpResponse<Nothing, Nothing> = HttpFailure(
    status = status,
    error = HttpError(cause, message)
)

inline fun <D> catching(block: () -> D) = try {
    responseOf(block())
} catch (e: Exception) {
    responseOf(e)
}

@OptIn(ExperimentalContracts::class, ExperimentalTypeInference::class)
inline fun <D> response(@BuilderInference builder: HttpResponseBuilder<D, *>.() -> Unit): HttpResponse<D, Nothing?> {
    contract {
        callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
    }
    return try {
        val responseBuilder = HttpResponseBuilder<D, Nothing?>()
        responseBuilder.apply(builder)
        responseBuilder.response()
    } catch (cause: Throwable) {
        responseOf(cause)
    }
}

@OptIn(ExperimentalContracts::class, ExperimentalTypeInference::class)
inline fun <D, I> responseWithInfo(@BuilderInference builder: HttpResponseBuilder<D, I>.() -> Unit): HttpResponse<D, I> {
    contract {
        callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
    }
    return try {
        val responseBuilder = HttpResponseBuilder<D, I>()
        responseBuilder.apply(builder)
        responseBuilder.response()
    } catch (cause: Throwable) {
        responseOf(cause)
    }
}