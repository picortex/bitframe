package pimonitor.server.portfolio

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class PortfolioModule(
    private val controller: PortfolioController
) : Module {
    override val name: String = "Portfolio"
    private val config get() = controller.config
    override val actions: List<Action> = listOf(
        Action("Load Portfolio", mapOf(), HttpRoute(HttpMethod.Post,config.pathV1.portfolioLoad){
            controller.load(it)
        })
    )
}