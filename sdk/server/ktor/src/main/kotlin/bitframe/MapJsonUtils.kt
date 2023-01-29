package bitframe

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject

fun Any?.toJsonElement(): JsonElement = when (this) {
    is Number -> JsonPrimitive(this)
    is String -> JsonPrimitive(this)
    is Char -> JsonPrimitive(this.toString())
    is UInt -> JsonPrimitive(toDouble())
    is UShort -> JsonPrimitive(toDouble())
    is ULong -> JsonPrimitive(toDouble())
    is UByte -> JsonPrimitive(toDouble())

    is Map<*, *> -> buildJsonObject {
        for ((key, value) in entries) put(key.toString(), value.toJsonElement())
    }

    is Collection<*> -> buildJsonArray {
        for (item in this@toJsonElement) add(item.toJsonElement())
    }

    else -> JsonNull
}