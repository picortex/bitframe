package bitframe

import bitframe.server.BitframeApplication
import bitframe.server.data.DAOProvider
import bitframe.server.http.HttpRequest
import bitframe.server.modules.Module
import bitframe.server.modules.authentication.modules.AuthenticationModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.serialization.mapper.Mapper
import java.io.File

class Application<P : DAOProvider>(
    val config: ApplicationConfig<P>
) : BitframeApplication<P>(config) {
    @JvmOverloads
    fun start(port: Int = 8080) = embeddedServer(CIO, port) {
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
                    val headers = call.request.headers.entries().associate { (k, v) ->
                        k to v.joinToString(",")
                    }
                    val body = call.receiveText()
                    val request = HttpRequest(rout.method, rout.path, headers, body)
                    val response = rout.handler(request)
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