package response

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import response.failure.decodeFailureFromString
import response.success.decodeSuccessFromString

fun <D> Json.decodeResponseFromString(
    dataSerializer: KSerializer<D>,
    json: String,
): Response<D, *> = try {
    decodeSuccessFromString(dataSerializer, json)
} catch (cause: Throwable) {
    try {
        decodeFailureFromString(json)
    } catch (err: Throwable) {
        cause.printStackTrace()
        err.printStackTrace()
        responseOf(Throwable("Failed to decode the response", cause))
    }
}

fun <D, I : Any> Json.decodeResponseWithInfoFromString(
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