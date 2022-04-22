package pimonitor.server.business.overview

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class BusinessOverviewModule(private val controller: BusinessOverviewController) : Module {
    private val path get() = controller.service.config.pathV1
    override val name: String = "Business Overview"
    override val actions: List<Action> = listOf(
        Action("Load Overview", mapOf(), HttpRoute(HttpMethod.Post, path.businessOverviewLoad) {
            controller.load(it)
        })
    )
}