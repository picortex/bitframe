package bitframe

import bitframe.http.HttpRequest
import bitframe.http.HttpRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.mapper.Mapper

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
                        println("Reached at ${rout.method} ${rout.path}")
                        val response = rout.runHandlerCatching(mapToHttpRequest(rout, call))
                        println("Reached at ")
                        call.respondText(response.body, contentType = ContentType.Application.Json, status = response.status)
                    }
                }

                get("/api/info") {
                    val text = (modules).map { it.info() }
                    call.respondText(Mapper { prettyPrint = true }.encodeToString(text))
                }
            }
        }.start(wait = true)
    }
}