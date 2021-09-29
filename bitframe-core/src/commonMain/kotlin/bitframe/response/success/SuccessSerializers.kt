package bitframe.response.success

import bitframe.response.Status
import bitframe.response.Success
import bitframe.response.payload.encodePayloadToString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun <D> Json.encodeSuccessToString(
    serializer: KSerializer<D>,
    success: Success<D, *>
): String {
    val statusJson = encodeToString(Status.serializer(), success.status)
    val payload = success.payload
    val successJson = encodePayloadToString(serializer, payload)
    val mapper = Mapper(this)
    val map = mapOf(
        Success<*, *>::status.name to mapper.decodeFromString(statusJson),
        Success<*, *>::payload.name to mapper.decodeFromString(successJson)
    )
    return mapper.encodeToString(map)
}

fun <D, I> Json.encodeSuccessToString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    success: Success<D, I>
): String {
    val statusJson = encodeToString(Status.serializer(), success.status)
    val successJson = encodePayloadToString(dataSerializer, infoSerializer, success.payload)
    val mapper = Mapper(this)
    val map = mapOf(
        Success<*, *>::status.name to mapper.decodeFromString(statusJson),
        Success<*, *>::payload.name to mapper.decodeFromString(successJson)
    )
    return mapper.encodeToString(map)
}