package bitframe.response.payload

import bitframe.response.Payload
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun <D> Json.decodePayloadFromString(
    serializer: KSerializer<D>,
    json: String
): Payload<out D, out Nothing?> {
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json)
    val dataJson = map[Payload<*, *>::data.name] as Map<String, Any?>
    val data = decodeFromString(serializer, mapper.encodeToString(dataJson))
    return payloadOf(data)
}

fun <D, I> Json.decodePayloadFromString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    json: String
): Payload<out D, out I> {
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json)
    val dataJson = map[Payload<*, *>::data.name] as Map<String, Any?>
    val infoJson = map[Payload<*, *>::info.name] as Map<String, Any?>
    val data = decodeFromString(dataSerializer, mapper.encodeToString(dataJson))
    val info = decodeFromString(infoSerializer, mapper.encodeToString(infoJson))
    return payloadOf(data, info)
}