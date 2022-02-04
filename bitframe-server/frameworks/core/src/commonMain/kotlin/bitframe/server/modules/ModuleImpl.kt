package bitframe.server.modules

import bitframe.server.actions.Action
import bitframe.server.http.HttpRoute
import io.ktor.http.*

class ModuleImpl<D : Any>(private val config: ModuleConfiguration<D>) : Module {
    override val name: String get() = config.name
    private val controller = config.controller
    private val basePath = config.basePath
    override val actions: List<Action> = listOf(
        Action("create", mapOf(), HttpRoute(HttpMethod.Post, "$basePath/") {
            controller.create(it.body)
        }),
        Action("single", mapOf(), HttpRoute(HttpMethod.Get, "$basePath/{uid}") {
            controller.load(it.body)
        }),
        Action("many", mapOf(), HttpRoute(HttpMethod.Get, "$basePath/all") {
            controller.load(it.body)
        }),
        Action("update", mapOf(), HttpRoute(HttpMethod.Put, "$basePath/") {
            controller.update(it.body)
        }),
        Action("delete", mapOf(), HttpRoute(HttpMethod.Delete, "$basePath/{uid}") {
            controller.delete(it.body)
        })
    )
}