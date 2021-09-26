package bitframe.response.response

import bitframe.response.Failure
import bitframe.response.Response
import bitframe.response.Success
import bitframe.response.failure.encodeFailureToString
import bitframe.response.success.encodeSuccessToString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

fun <D> Json.encodeResponseToString(
    serializer: KSerializer<D>,
    response: Response<D, *>
): String = when (response) {
    is Failure -> encodeFailureToString(response)
    is Success -> encodeSuccessToString(serializer, response)
}

fun <D, I> Json.encodeResponseToString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    response: Response<D, I>
): String = when (response) {
    is Failure -> encodeFailureToString(response)
    is Success -> encodeSuccessToString(dataSerializer, infoSerializer, response)
}

@PublishedApi
internal val json = Json { encodeDefaults = true }

inline fun <reified D> Response<D, *>.toJson(): String {
    val dataSerializer = serializer<D>()
    return json.encodeResponseToString(dataSerializer, this)
}

inline fun <reified D, reified I> Response<D, I>.toJsonWithInfo(): String {
    val dataSerializer = serializer<D>()
    val infoSerializer = serializer<I>()
    return json.encodeResponseToString(dataSerializer, infoSerializer, this)
}