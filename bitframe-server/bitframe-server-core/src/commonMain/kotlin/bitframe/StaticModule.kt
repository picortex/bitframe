package bitframe

import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import bitframe.types.TypeInfo
import bitframe.types.processTypes
import io.ktor.http.*
import kotlin.jvm.JvmStatic

open class StaticModule<T>(singular: String, plural: String, val types: List<TypeInfo>) : Module {
    companion object {
        @PublishedApi
        internal val error = IllegalArgumentException("Can't determine name")

        @JvmStatic
        inline fun <reified E> create(
            singular: String = E::class.simpleName?.lowercase() ?: throw error,
            plural: String = "${singular}s"
        ): StaticModule<E> {
            val types = listOf(processTypes<E>())
            return StaticModule(singular, plural, types)
        }
    }

    override val routes: List<HttpRoute> = listOf(
        HttpRoute(HttpMethod.Post, "/$singular") {
            HttpResponse(HttpStatusCode.OK)
        },
        HttpRoute(HttpMethod.Get, "/$plural") {
            HttpResponse(HttpStatusCode.OK)
        },
        HttpRoute(HttpMethod.Put, "/$singular") {
            HttpResponse(HttpStatusCode.OK)
        }
    )
}