package pimonitor.server.businesses

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class BusinessesModule(
    private val controller: BusinessesController
) : Module {
    private val path = controller.service.config.pathV1
    override val name = "Businesses"
    override val actions: List<Action> = listOf(
        Action("create", mapOf(), HttpRoute(HttpMethod.Post, path.businessesCreate) {
            controller.create(it)
        }),
        Action("all", mapOf(), HttpRoute(HttpMethod.Post, path.businessesAll) {
            controller.all(it)
        }),
        Action("delete", mapOf(), HttpRoute(HttpMethod.Post, path.businessesDelete) {
            controller.delete(it)
        }),
        Action("load business", mapOf(), HttpRoute(HttpMethod.Post, path.businessesLoad) {
            controller.load(it)
        }),
        Action("update business info", mapOf(), HttpRoute(HttpMethod.Post, path.businessesUpdate) {
            controller.updated(it)
        }),
    )
}