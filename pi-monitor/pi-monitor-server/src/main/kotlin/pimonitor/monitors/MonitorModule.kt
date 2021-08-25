package pimonitor.monitors

import bitframe.server.actions.Action
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import bitframe.server.modules.Module
import io.ktor.http.*
import io.ktor.utils.io.*
import pimonitor.Monitor

class MonitorModule : Module {
    override val name = "monitors"
    override val actions: List<Action> = listOf(
        Action("create", mapOf(), HttpRoute(HttpMethod.Post, "/") {
            HttpResponse(HttpStatusCode.OK)
        })
    )
}