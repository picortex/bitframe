package pimonitor.server.businesses

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*

class BusinessModule(
    private val controller: BusinessController
) : Module {
    val basePath = "/api/businesses"
    override val name = "Businesses"
    override val actions: List<Action> = listOf(
        Action("create", mapOf(), HttpRoute(HttpMethod.Post, "$basePath/create") {
            controller.create(it)
        }),
        Action("all", mapOf(), HttpRoute(HttpMethod.Post, "$basePath/all") {
            controller.all(it)
        })
    )
}