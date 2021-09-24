package bitframe.http.payload

import bitframe.http.HttpPayload
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun <D> Json.encodePayloadToString(
    serializer: KSerializer<D>,
    payload: HttpPayload<D, *>
): String {
    val json = encodeToString(serializer, payload.data)
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json).toMap()
    return mapper.encodeToString(mapOf(HttpPayload<*, *>::data.name to map))
}

fun <D, I : Any> Json.encodePayloadToString(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>,
    payload: HttpPayload.Informed<D, I>
): String {
    val dataJson = encodeToString(dataSerializer, payload.data)
    val infoJson = encodeToString(infoSerializer.nullable, payload.info)
    val mapper = Mapper(this)
    val dataMap = mapper.decodeFromString(dataJson).toMap()
    val infoMap = mapper.decodeFromString(infoJson).toMap()
    return mapper.encodeToString(
        mapOf(
            HttpPayload<*, *>::data.name to dataMap,
            HttpPayload.Informed<*, *>::info.name to infoMap
        )
    )
}