package bitframe.http.response

import bitframe.http.HttpFailure
import bitframe.http.HttpResponse
import bitframe.http.HttpSuccess
import bitframe.http.failure.encodeFailureToString
import bitframe.http.success.encodeSuccessToString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

fun <D> Json.encodeResponseToString(
    serializer: KSerializer<D>,
    response: HttpResponse<D, *>
): String = when (response) {
    is HttpFailure -> encodeFailureToString(response)
    is HttpSuccess -> encodeSuccessToString(serializer, response)
}

fun <D, I : Any> Json.encodeResponseToString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    response: HttpResponse<D, I>
): String = when (response) {
    is HttpFailure -> encodeFailureToString(response)
    is HttpSuccess -> encodeSuccessToString(dataSerializer, infoSerializer, response)
}