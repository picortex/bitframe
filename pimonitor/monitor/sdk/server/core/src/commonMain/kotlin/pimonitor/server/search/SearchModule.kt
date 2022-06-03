package pimonitor.server.search

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class SearchModule(private val controller: SearchController) : Module {
    override val name: String = "Search"
    private val config get() = controller.config
    override val actions: List<Action> = listOf(
        Action("search", mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.search) {
            controller.search(it)
        })
    )
}