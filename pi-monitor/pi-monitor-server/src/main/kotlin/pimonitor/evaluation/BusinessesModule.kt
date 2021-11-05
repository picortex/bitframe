package pimonitor.evaluation

import bitframe.server.actions.Action
import bitframe.server.http.HttpRoute
import bitframe.server.modules.Module
import io.ktor.http.*

class BusinessesModule(
    val controller: BusinessController
) : Module {
    override val name: String = "businesses"
    override val actions: List<Action> = listOf(
        Action("create", mapOf(), HttpRoute(HttpMethod.Post, "/api/businesses") {
            controller.create(it)
        }),
        Action("all", mapOf(), HttpRoute(HttpMethod.Get, "/api/businesses") {
            controller.all()
        })
    )
}