package response

import io.ktor.http.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import response.payload.payloadOf
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.experimental.ExperimentalTypeInference

// Success
fun <D> responseOf(data: D): Response<D, Nothing?> = Success(Status(HttpStatusCode.OK), payloadOf(data))
fun <D, I : Any> responseOf(data: D, info: I): Response<D, I> = Success(Status(HttpStatusCode.OK), Payload.of(data, info))
fun <D> responseOf(status: Status, data: D): Response<D, Nothing?> = Success(status, payloadOf(data))
fun <D> responseOf(code: HttpStatusCode, data: D): Response<D, Nothing?> = Success(Status(code), payloadOf(data))
fun <D, I> responseOf(code: HttpStatusCode, data: D, info: I): Response<D, I> = Success(Status(code), payloadOf(data, info))
fun <D, I> responseOf(status: Status, data: D, info: I): Response<D, I> = Success(status, Payload(data, info))

// Failures
fun responseOf(
    cause: Throwable,
    message: String? = null
): Response<Nothing, Nothing> = responseOf(
    status = Status(HttpStatusCode.BadRequest),
    cause = cause,
    message = message
)

fun responseOf(
    status: Status,
    cause: Throwable,
    message: String? = null
): Response<Nothing, Nothing> = Failure(
    status = status,
    error = Error(cause, message)
)

@OptIn(ExperimentalContracts::class, ExperimentalTypeInference::class)
inline fun <D> response(@BuilderInference builder: ResponseBuilder<D, Nothing?>.() -> Unit): Response<D, Nothing?> {
    contract { callsInPlace(builder) }
    return try {
        val responseBuilder = ResponseBuilder<D, Nothing?>()
        responseBuilder.apply(builder)
        responseBuilder.response()
    } catch (cause: Throwable) {
        responseOf(cause, cause.message)
    }
}

@OptIn(ExperimentalContracts::class, ExperimentalTypeInference::class)
inline fun <D, I> responseWithInfo(@BuilderInference builder: ResponseBuilder<D, I>.() -> Unit): Response<D, I> {
    contract { callsInPlace(builder) }
    return try {
        val responseBuilder = ResponseBuilder<D, I>()
        responseBuilder.apply(builder)
        responseBuilder.response()
    } catch (cause: Throwable) {
        responseOf(cause, cause.message)
    }
}

fun <D, I : Any> Response<D, I>.body(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>
): String = Json.encodeResponseToString(dataSerializer, infoSerializer, this)

fun <D> Response<D, *>.body(
    serializer: KSerializer<D>
): String = Json.encodeResponseToString(serializer, this)