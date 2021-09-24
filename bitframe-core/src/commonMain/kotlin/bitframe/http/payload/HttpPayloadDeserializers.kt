package bitframe.http.payload

import bitframe.http.HttpPayload
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun <D> Json.decodePayloadFromString(
    serializer: KSerializer<D>,
    json: String
): HttpPayload<D, Nothing?> {
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json)
    val dataJson = map[HttpPayload<*, *>::data.name] as Map<String, Any?>
    val data = decodeFromString(serializer, mapper.encodeToString(dataJson))
    return payloadOf(data)
}

fun <D, I : Any> Json.decodePayloadFromString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    json: String
): HttpPayload<D, I> {
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json)
    val dataJson = map[HttpPayload<*, *>::data.name] as Map<String, Any?>
    val infoJson = map[HttpPayload<*, *>::info.name] as Map<String, Any?>
    val data = decodeFromString(dataSerializer, mapper.encodeToString(dataJson))
    val info = decodeFromString(infoSerializer, mapper.encodeToString(infoJson))
    return payloadOf(data, info)
}