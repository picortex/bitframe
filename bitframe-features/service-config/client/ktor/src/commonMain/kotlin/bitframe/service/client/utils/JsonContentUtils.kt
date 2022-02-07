package bitframe.service.client.utils

import bitframe.service.client.Session
import bitframe.service.client.requests.RequestBody
import bitframe.service.client.requests.RequestBody.UnAuthorized.Companion
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

@Deprecated("Use json.RequestBody() instead")
inline fun <reified T> JsonContent(obj: T, serializer: KSerializer<T> = serializer()) = TextContent(
    text = Json.encodeToString(serializer, obj),
    contentType = ContentType.Application.Json
)

inline fun <reified T> Json.authorizedBodyOf(
    session: Session.SignedIn,
    body: T,
    serializer: KSerializer<T> = serializer()
) = TextContent(
    text = encodeToString(RequestBody.Authorized.serializer(serializer), RequestBody.Authorized(session, body)),
    contentType = ContentType.Application.Json
)

inline fun <reified T> Json.unAuthorizedBodyOf(
    appId: String,
    body: T,
    serializer: KSerializer<T> = serializer()
) = TextContent(
    text = encodeToString(RequestBody.UnAuthorized.serializer(serializer), RequestBody.UnAuthorized(appId, body)),
    contentType = ContentType.Application.Json
)