package bitframe.server.http

import bitframe.response.Response
import bitframe.response.response.encodeResponseToString
import io.ktor.http.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.jvm.JvmName

@PublishedApi
internal val json = Json { encodeDefaults = true }

inline fun <reified D> Response<D, *>.toHttpResponse(
    serializer: KSerializer<D> = serializer()
): HttpResponse = HttpResponse(
    status = HttpStatusCode(status.code, status.message),
    body = json.encodeResponseToString(serializer, this)
)

@JvmName("ListResponse")
inline fun <reified D> Response<List<D>, *>.toHttpResponse(
    serializer: KSerializer<List<D>> = ListSerializer(serializer<D>()) as KSerializer<List<D>>
): HttpResponse = HttpResponse(
    status = HttpStatusCode(status.code, status.message),
    body = json.encodeResponseToString(serializer, this)
)

inline fun <reified D, reified I> Response<D, I>.toHttpResponseWithInfo(
    dataSerializer: KSerializer<D> = serializer(),
    infoSerializer: KSerializer<I> = serializer()
): HttpResponse = HttpResponse(
    status = HttpStatusCode(status.code, status.message),
    body = json.encodeResponseToString(dataSerializer, infoSerializer, this)
)

@JvmName("ListResponseWithInfo")
inline fun <reified D, reified I> Response<List<D>, I>.toHttpResponseWithInfo(
    dataSerializer: KSerializer<List<D>> = ListSerializer(serializer<D>()) as KSerializer<List<D>>,
    infoSerializer: KSerializer<I> = serializer()
): HttpResponse = HttpResponse(
    status = HttpStatusCode(status.code, status.message),
    body = json.encodeResponseToString(dataSerializer, infoSerializer, this)
)