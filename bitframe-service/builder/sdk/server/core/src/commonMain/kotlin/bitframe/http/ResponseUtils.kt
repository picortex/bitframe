package bitframe.http

import response.Response
import response.encodeResponseToString
import io.ktor.http.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.jvm.JvmName

@PublishedApi
internal val json = Json { encodeDefaults = true }

inline fun <reified D> Response<D, *>.toHttpResponse() = toHttpResponse(serializer())

fun <D> Response<D, *>.toHttpResponse(
    serializer: KSerializer<D>
): HttpResponse = HttpResponse(
    status = HttpStatusCode(status.code, status.message),
    body = json.encodeResponseToString(serializer, this)
)

@JvmName("ListResponse")
inline fun <reified D> Response<List<D>, *>.toHttpResponse() = toHttpResponse(ListSerializer(serializer()))

@JvmName("ListResponse")
fun <D> Response<List<D>, *>.toHttpResponse(
    serializer: KSerializer<List<D>>
): HttpResponse = HttpResponse(
    status = HttpStatusCode(status.code, status.message),
    body = json.encodeResponseToString(serializer, this)
)

inline fun <reified D, reified I> Response<D, I>.toHttpResponseWithInfo() = toHttpResponseWithInfo(serializer(), serializer())

fun <D, I> Response<D, I>.toHttpResponseWithInfo(
    dataSerializer: KSerializer<D>,
    infoSerializer: KSerializer<I>
): HttpResponse = HttpResponse(
    status = HttpStatusCode(status.code, status.message),
    body = json.encodeResponseToString(dataSerializer, infoSerializer, this)
)

@JvmName("ListResponseWithInfo")
inline fun <reified D, reified I> Response<List<D>, I>.toHttpResponseWithInfo() = toHttpResponseWithInfo(ListSerializer(serializer()), serializer())

@JvmName("ListResponseWithInfo")
fun <D, I> Response<List<D>, I>.toHttpResponseWithInfo(
    dataSerializer: KSerializer<List<D>>,
    infoSerializer: KSerializer<I>
): HttpResponse = HttpResponse(
    status = HttpStatusCode(status.code, status.message),
    body = json.encodeResponseToString(dataSerializer, infoSerializer, this)
)