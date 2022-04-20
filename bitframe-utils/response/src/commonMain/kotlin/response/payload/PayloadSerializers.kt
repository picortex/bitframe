package response.payload

import response.Payload
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

internal fun <D> Json.encodePayloadToString(
    serializer: KSerializer<D>,
    payload: Payload<out D, *>
): String {
    val holder = "holder"
    val json = """{"$holder": ${encodeToString(serializer, payload.data)}}"""
    val mapper = Mapper(this)
    val value = mapper.decodeFromString(json)[holder]
    return mapper.encodeToString(mapOf(Payload<*, *>::data.name to value))
}

internal fun <D, I> Json.encodePayloadToString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    payload: Payload<out D, out I>
): String {
    val holder = "holder"
    val dataJson = """{"$holder":${encodeToString(dataSerializer, payload.data)}}"""
    val infoJson = """{"$holder": ${encodeToString(infoSerializer, payload.info)}}"""
    val mapper = Mapper(this)
    val dataValue = mapper.decodeFromString(dataJson)[holder]
    val infoValue = mapper.decodeFromString(infoJson)[holder]
    return mapper.encodeToString(
        mapOf(
            Payload<*, *>::data.name to dataValue,
            Payload<*, *>::info.name to infoValue
        )
    )
}