package bitframe.response.response

import bitframe.response.Response
import bitframe.response.failure.decodeFailureFromString
import bitframe.response.success.decodeSuccessFromString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

fun <D> Json.decodeResponseFromString(
    dataSerializer: KSerializer<D>,
    json: String,
): Response<D, *> = try {
    decodeSuccessFromString(dataSerializer, json)
} catch (cause: Throwable) {
    try {
        cause.printStackTrace()
        decodeFailureFromString(json)
    } catch (err: Throwable) {
        responseOf(Throwable("Failed to decode the response", cause))
    }
}

fun <D, I : Any> Json.decodeResponseFromString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    json: String,
): Response<D, I> = try {
    decodeSuccessFromString(dataSerializer, infoSerializer, json)
} catch (cause: Throwable) {
    try {
        decodeFailureFromString(json)
    } catch (err: Throwable) {
        responseOf(Throwable("Failed to decode the response", cause))
    }
}