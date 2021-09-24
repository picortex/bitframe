package bitframe.http.success

import bitframe.http.HttpStatus
import bitframe.http.HttpSuccess
import bitframe.http.payload.decodePayloadFromString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

internal const val payloadKey = "payload"

fun <D> Json.decodeSuccessFromString(
    serializer: KSerializer<D>,
    json: String
): HttpSuccess.Uninformed<D> {
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json)
    val statusMap = map[HttpSuccess<*, *>::status.name] as? Map<String, *> ?: throw Throwable(
        "${HttpSuccess<*, *>::status.name} field is missing in the response JSON"
    )
    val payloadMap = map[payloadKey] as? Map<String, *> ?: throw Throwable(
        "$payloadKey field is missing the response json"
    )
    val status = decodeFromString(HttpStatus.serializer(), mapper.encodeToString(statusMap))
    val payload = decodePayloadFromString(serializer, mapper.encodeToString(payloadMap))
    return HttpSuccess.Uninformed(status, payload)
}

fun <D, I : Any> Json.decodeSuccessFromString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    json: String
): HttpSuccess.Informed<D, I> {
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json)
    val statusMap = map[HttpSuccess<*, *>::status.name] as? Map<String, *> ?: throw Throwable(
        "${HttpSuccess<*, *>::status.name} field is missing in the response JSON"
    )
    val payloadMap = map[payloadKey] as? Map<String, *> ?: throw Throwable(
        "$payloadKey field is missing the response json"
    )
    val status = decodeFromString(HttpStatus.serializer(), mapper.encodeToString(statusMap))
    val payload = decodePayloadFromString(dataSerializer, infoSerializer, mapper.encodeToString(payloadMap))
    return HttpSuccess.Informed(status, payload)
}