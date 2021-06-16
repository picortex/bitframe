package bitframe

import bitframe.http.HttpRequest
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

class Application(modules: List<Module>) : BitframeApplication(modules) {
    fun start(port: Int = 8080) = embeddedServer(CIO, port) {
        routing {
            get("/") {
                call.respondText("It works")
            }

            for (rout in modules.flatMap { it.actions.map { a -> a.route } }) route(rout.path, rout.method) {
                handle {
                    val headers = call.request.headers.entries().map { (k, v) ->
                        k to v.joinToString(",")
                    }.toMap()
                    val body = call.receiveText()
                    val request = HttpRequest(rout.method, rout.path, headers, body)
                    val response = rout.handler(request)
                    call.respondText(response.body, contentType = ContentType.Application.Json)
                }
            }
        }
    }.start(wait = true)
}