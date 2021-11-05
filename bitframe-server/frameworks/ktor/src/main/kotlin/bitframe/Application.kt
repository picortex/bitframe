package bitframe

import bitframe.response.Failure
import bitframe.response.Status
import bitframe.response.response.responseOf
import bitframe.server.BitframeApplication
import bitframe.server.BitframeService
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpRoute
import bitframe.server.http.toHttpResponse
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
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
                allowCredentials = true
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
                        val response = try {
                            rout.handler(mapToHttpRequest(rout, call))
                        } catch (cause: Throwable) {
                            responseOf(Status(InternalServerError), cause).toHttpResponse<Failure>()
                        }
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