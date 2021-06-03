package bitframe

import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import io.ktor.http.*

open class StaticModule(singular: String, plural: String) : Module {
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