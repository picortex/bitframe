package bitframe.response.payload

import bitframe.response.Payload
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun <D> Json.encodePayloadToString(
    serializer: KSerializer<D>,
    payload: Payload<out D, *>
): String {
    val json = encodeToString(serializer, payload.data)
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json).toMap()
    return mapper.encodeToString(mapOf(Payload<*, *>::data.name to map))
}

fun <D, I> Json.encodePayloadToString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    payload: Payload<out D, out I>
): String {
    val dataJson = encodeToString(dataSerializer, payload.data)
    val infoJson = encodeToString(infoSerializer, payload.info)
    val mapper = Mapper(this)
    val dataMap = mapper.decodeFromString(dataJson).toMap()
    val infoMap = mapper.decodeFromString(infoJson).toMap()
    return mapper.encodeToString(
        mapOf(
            Payload<*, *>::data.name to dataMap,
            Payload<*, *>::info.name to infoMap
        )
    )
}