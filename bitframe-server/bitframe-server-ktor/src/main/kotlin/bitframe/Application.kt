package bitframe

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

class Application(modules: List<Module>) : BitframeApplication(modules) {
    fun start(port: Int = 8080) {
        embeddedServer(CIO, port) {
            routing {
                get("/") {
                    call.respondText("It works")
                }
            }
        }.start(wait = true)
    }
}