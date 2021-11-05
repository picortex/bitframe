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
    val dataValue = map[Payload<*, *>::data.name]
    val data = if (dataValue is Map<*, *>) {
        decodeFromString(serializer, mapper.encodeToString(dataValue as Map<String, *>))
    } else {
        decodeFromString(serializer, mapper.encodeToString(dataValue as List<Map<String, *>>))
    }
    return payloadOf(data)
}

fun <D, I> Json.decodePayloadFromString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    json: String
): Payload<out D, out I> {
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json)
    val infoValue = map[Payload<*, *>::info.name] as Map<String, Any?>
    val dataValue = map[Payload<*, *>::data.name]
    val info = decodeFromString(infoSerializer, mapper.encodeToString(infoValue))
    val data = if (dataValue is Map<*, *>) {
        decodeFromString(dataSerializer, mapper.encodeToString(dataValue as Map<String, *>))
    } else {
        decodeFromString(dataSerializer, mapper.encodeToString(dataValue as List<Map<String, *>>))
    }
    return payloadOf(data, info)
}