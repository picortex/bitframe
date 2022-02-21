package bitframe.client

import bitframe.core.RequestBody
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

inline fun <reified T> Json.of(
    body: RequestBody<T>,
    serializer: KSerializer<T> = serializer()
) = when (body) {
    is RequestBody.Authorized -> of(body, serializer)
    is RequestBody.UnAuthorized -> of(body, serializer)
}

inline fun <reified T> Json.of(
    body: RequestBody.Authorized<T>,
    serializer: KSerializer<T> = serializer()
) = TextContent(
    text = encodeToString(RequestBody.Authorized.serializer(serializer), body),
    contentType = ContentType.Application.Json
)

inline fun <reified T> Json.of(
    body: RequestBody.UnAuthorized<T>,
    serializer: KSerializer<T> = serializer()
) = TextContent(
    text = encodeToString(RequestBody.UnAuthorized.serializer(serializer), body),
    contentType = ContentType.Application.Json
)