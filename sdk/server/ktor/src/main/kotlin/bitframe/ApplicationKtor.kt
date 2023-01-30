package bitframe

import bitframe.http.HttpRequest
import bitframe.http.HttpRoute
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.default
import io.ktor.server.http.content.file
import io.ktor.server.http.content.files
import io.ktor.server.http.content.static
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.receiveText
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class ApplicationKtor<S>(
    override val config: ApplicationConfig<S>
) : Application<S>(config) {

    suspend fun mapToHttpRequest(route: HttpRoute, call: ApplicationCall) = HttpRequest(
        method = route.method,
        path = route.path,
        headers = call.request.headers.entries().associate { (k, v) ->
            k to v.joinToString(",")
        },
        queryParameters = call.parameters.entries().associate { (k, v) ->
            k to (v.firstOrNull() ?: "")
        },
        body = call.receiveText()
    )

    @JvmOverloads
    fun start(port: Int = 8080) {
        runBlocking { onStart(config.service) }
        embeddedServer(CIO, port) {
            install(CORS) {
                allowMethod(HttpMethod.Options)
                allowMethod(HttpMethod.Get)
                allowMethod(HttpMethod.Post)
                allowMethod(HttpMethod.Put)
                allowMethod(HttpMethod.Delete)
                allowMethod(HttpMethod.Patch)
                allowHeader(HttpHeaders.AccessControlAllowHeaders)
                allowHeader(HttpHeaders.ContentType)
                allowHeader(HttpHeaders.AccessControlAllowOrigin)
                anyHost()
            }
            routing {
                static("/") {
                    files(".")
                    file("main.bundle.js")
                    default("index.html")
                }

                for (rout in modules.flatMap { it.actions.map { a -> a.route } }) route(rout.path, rout.method) {
                    handle {
                        val response = rout.runHandlerCatching(mapToHttpRequest(rout, call))
                        call.respondText(response.body, contentType = ContentType.Application.Json, status = response.status)
                    }
                }

                get("/api/info") {
                    val info = (modules).map { it.info() }
                    val codec = Json { prettyPrint = true }
                    call.respondText(codec.encodeToString(info.toJsonElement()))
                }
            }
        }.start(wait = true)
    }
}