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
    val payload = success.payload
    val successJson = encodePayloadToString(serializer, payload)
    val mapper = Mapper(this)
    val map = mapOf(
        HttpSuccess<*, *>::status.name to mapper.decodeFromString(statusJson),
        HttpSuccess<*, *>::payload.name to mapper.decodeFromString(successJson)
    )
    return mapper.encodeToString(map)
}

fun <D, I : Any> Json.encodeSuccessToString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    success: HttpSuccess<D, I>
): String {
    val statusJson = encodeToString(HttpStatus.serializer(), success.status)
    val successJson = encodePayloadToString(dataSerializer, infoSerializer, success.payload)
    val mapper = Mapper(this)
    val map = mapOf(
        HttpSuccess<*, *>::status.name to mapper.decodeFromString(statusJson),
        HttpSuccess<*, *>::payload.name to mapper.decodeFromString(successJson)
    )
    return mapper.encodeToString(map)
}