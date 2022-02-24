package pimonitor.server.search

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*

class SearchModule(private val controller: SearchController) : Module {
    override val name: String = "Search"
    override val actions: List<Action> = listOf(
        Action("search", mapOf(), HttpRoute(HttpMethod.Post, "/api/search") {
            controller.search(it)
        })
    )
}