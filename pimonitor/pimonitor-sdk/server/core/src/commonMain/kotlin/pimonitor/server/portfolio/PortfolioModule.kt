package pimonitor.server.portfolio

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*

class PortfolioModule(
    private val controller: PortfolioController
) : Module {
    override val name: String = "Portfolio"
    override val actions: List<Action> = listOf(
        Action("Load Portfolio", mapOf(), HttpRoute(HttpMethod.Post,"/api/portfolio/load"){
            controller.load(it)
        })
    )
}