package bitframe.service.client.utils

import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

inline fun <reified T> JsonContent(obj: T, serializer: KSerializer<T> = serializer()) = TextContent(
    text = Json.encodeToString(serializer, obj),
    contentType = ContentType.Application.Json
)