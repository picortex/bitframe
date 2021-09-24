package bitframe.http.response

import bitframe.http.HttpResponse
import bitframe.http.failure.decodeFailureFromString
import bitframe.http.success.decodeSuccessFromString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

fun <D> Json.decodeResponseFromString(
    dataSerializer: KSerializer<D>,
    json: String,
): HttpResponse<D, *> = try {
    decodeSuccessFromString(dataSerializer, json)
} catch (cause: Throwable) {
    try {
        decodeFailureFromString(json)
    } catch (err: Throwable) {
        responseOf(Throwable("Failed to decode the response", cause))
    }
}

fun <D, I : Any> Json.decodeResponseFromString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    json: String,
): HttpResponse<D, I> = try {
    decodeSuccessFromString(dataSerializer, infoSerializer, json)
} catch (cause: Throwable) {
    try {
        decodeFailureFromString(json)
    } catch (err: Throwable) {
        responseOf(Throwable("Failed to decode the response", cause))
    }
}