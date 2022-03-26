package pimonitor.server.business.operations

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import kotlinx.datetime.Clock
import pimonitor.core.business.params.LoadReportParams
import pimonitor.server.utils.pathV1

class BusinessOperationsModule(
    val controller: BusinessOperationsController
) : Module {
    override val name: String = "Business Operations"

    private val path get() = controller.service.config.pathV1
    override val actions: List<Action> = listOf(
        Action("Operational Dashboard", mapOf(), HttpRoute(HttpMethod.Post, path.businessOperationalDashboard) {
            controller.dashboard(it)
        })
    )
}