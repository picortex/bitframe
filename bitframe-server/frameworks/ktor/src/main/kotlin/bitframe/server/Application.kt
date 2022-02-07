package bitframe.server

import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.mapper.Mapper

open class Application<S : BitframeService>(
    override val config: ApplicationConfig<S>
) : BitframeApplication<S>(config) {

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
            println("Serving files from ${config.client.absolutePath}")
            install(CORS) {
                method(HttpMethod.Options)
                method(HttpMethod.Get)
                method(HttpMethod.Post)
                method(HttpMethod.Put)
                method(HttpMethod.Delete)
                method(HttpMethod.Patch)
                header(HttpHeaders.AccessControlAllowHeaders)
                header(HttpHeaders.ContentType)
                header(HttpHeaders.AccessControlAllowOrigin)
                anyHost()
            }
            routing {
                static("/") {
                    staticRootFolder = config.client.absoluteFile
                    files(".")
                    file("main.bundle.js")
                    default("index.html")
                }

                val allModules = modules + authenticationModule
                for (rout in allModules.flatMap { it.actions.map { a -> a.route } }) route(rout.path, rout.method) {
                    handle {
                        val response = rout.runHandlerCatching(mapToHttpRequest(rout, call))
                        call.respondText(response.body, contentType = ContentType.Application.Json, status = response.status)
                    }
                }

                get("/api/info") {
                    val text = (modules + authenticationModule).map { it.info() }
                    call.respondText(Mapper { prettyPrint = true }.encodeToString(text))
                }
            }
        }.start(wait = true)
    }
}