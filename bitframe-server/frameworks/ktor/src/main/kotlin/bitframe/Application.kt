package bitframe

import bitframe.server.BitframeApplication
import bitframe.server.http.HttpRequest
import bitframe.server.modules.Module
import bitframe.server.modules.authentication.AuthenticationModule
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.serialization.mapper.Mapper
import java.io.File

class Application(
    val client: File,
    authenticationModule: AuthenticationModule,
    modules: List<Module>
) : BitframeApplication(authenticationModule, modules) {
    @JvmOverloads
    fun start(port: Int = 8080) = embeddedServer(CIO, port) {
        println("Serving files from ${client.absolutePath}")
        routing {
            static("/") {
                staticRootFolder = client.absoluteFile
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
                    call.respondText(response.body, contentType = ContentType.Application.Json)
                }
            }

            get("/info") {
                val text = (modules + authenticationModule).map { it.info() }
                call.respondText(Mapper { prettyPrint = true }.encodeToString(text))
            }
        }
    }.start(wait = true)
}