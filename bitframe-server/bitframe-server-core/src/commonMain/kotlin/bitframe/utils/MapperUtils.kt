package bitframe.utils

import kotlinx.serialization.mapper.Mapper

fun Mapper.encodeToString(list: List<Map<String, *>>) = list.joinToString(
    separator = ",", prefix = "[", postfix = "]"
) { encodeToString(it) }

fun Mapper.decodeListFromString(json: String): List<Map<String, *>> {
    val raw = """{"data":$json}"""
    val data: List<Map<String, *>> by decodeFromString(raw)
    return data
}