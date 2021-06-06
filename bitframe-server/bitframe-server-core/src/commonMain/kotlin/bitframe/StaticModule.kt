package bitframe

import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import bitframe.types.TypeInfo
import bitframe.types.processTypes
import bitframe.utils.encodeToString
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper
import kotlin.jvm.JvmStatic

open class StaticModule<T>(
    singular: String,
    plural: String,
    val types: List<TypeInfo>,
    val db: DataSource
) : Module {
    companion object {
        @PublishedApi
        internal val error = IllegalArgumentException("Can't determine module name")

        @JvmStatic
        inline fun <reified E> create(
            singular: String = E::class.simpleName?.lowercase() ?: throw error,
            plural: String = "${singular}s",
            dataSource: DataSource
        ): StaticModule<E> {
            val types = listOf(processTypes<E>())
            return StaticModule(singular, plural, types, dataSource)
        }
    }

    override val routes: List<HttpRoute> = listOf(
        HttpRoute(HttpMethod.Post, "/$singular") {
            val body = it.body ?: return@HttpRoute HttpResponse(HttpStatusCode.BadRequest)
            val map = Mapper.decodeFromString(body)
            HttpResponse(HttpStatusCode.OK, Mapper.encodeToString(db.create(map)))
        },
        HttpRoute(HttpMethod.Get, "/$plural") {
            val list = db.all(mapOf<String, Any>())
            HttpResponse(HttpStatusCode.OK, Mapper.encodeToString(list))
        },
        HttpRoute(HttpMethod.Put, "/$singular") {
            HttpResponse(HttpStatusCode.OK)
        }
    )
}