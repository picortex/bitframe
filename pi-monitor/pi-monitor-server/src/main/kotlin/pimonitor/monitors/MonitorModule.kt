package pimonitor.monitors

import bitframe.server.actions.Action
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import bitframe.server.modules.Module
import io.ktor.http.*

class MonitorModule(
    val controller: MonitorsController
) : Module {
    override val name = "monitors"
    override val actions: List<Action> = listOf(
        Action("create", mapOf(), HttpRoute(HttpMethod.Post, "/api/monitors") {
            HttpResponse(HttpStatusCode.OK)
        }),
        Action("load", mapOf(), HttpRoute(HttpMethod.Get, "/api/monitors/{uid}") {
//            controller.get(it)
            TODO()
        }),
        Action("load-with-user-ref", mapOf(), HttpRoute(HttpMethod.Post, "/api/monitors/user") {
//            controller.getWithUser(it)
            TODO()
        })
    )
}