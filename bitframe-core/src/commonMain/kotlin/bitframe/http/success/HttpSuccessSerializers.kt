package bitframe.http.success

import bitframe.http.HttpPayload
import bitframe.http.HttpStatus
import bitframe.http.HttpSuccess
import bitframe.http.payload.encodePayloadToString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun <D> Json.encodeSuccessToString(
    serializer: KSerializer<D>,
    success: HttpSuccess<D, *>
): String {
    val statusJson = encodeToString(HttpStatus.serializer(), success.status)
    val payload = when (success) {
        is HttpSuccess.Informed -> success.payload
        is HttpSuccess.Uninformed -> success.payload
    }
    val successJson = encodePayloadToString(serializer, payload)
    val mapper = Mapper(this)
    val map = mapOf(
        HttpSuccess<*, *>::status.name to mapper.decodeFromString(statusJson),
        payloadKey to mapper.decodeFromString(successJson)
    )
    return mapper.encodeToString(map)
}

fun <D, I : Any> Json.encodeSuccessToString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    success: HttpSuccess<D, I>
): String {
    val statusJson = encodeToString(HttpStatus.serializer(), success.status)
    val payload = when (success) {
        is HttpSuccess.Informed -> success.payload
        is HttpSuccess.Uninformed -> success.payload
    }
    val successJson = when (payload) {
        is HttpPayload.Informed -> encodePayloadToString(dataSerializer, infoSerializer, payload)
        is HttpPayload.Uniformed -> encodePayloadToString(dataSerializer, payload)
    }
    val mapper = Mapper(this)
    val map = mapOf(
        HttpSuccess<*, *>::status.name to mapper.decodeFromString(statusJson),
        payloadKey to mapper.decodeFromString(successJson)
    )
    return mapper.encodeToString(map)
}