package pimonitor.server.business.investments

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class BusinessInvestmentsModule(
    val controller: BusinessInvestmentsController
) : Module {
    override val name: String = "Business Investments Module"
    val path get() = controller.service.config.pathV1
    override val actions: List<Action> = listOf(
        Action("Create Investment", mapOf(), HttpRoute(HttpMethod.Post, path.businessInvestmentsCapture) {
            controller.capture(it)
        }),
        Action("Load All Investments", mapOf(), HttpRoute(HttpMethod.Post, path.businessInvestmentsAll) {
            controller.all(it)
        }),
        Action("Create a disbursement", mapOf(), HttpRoute(HttpMethod.Post, path.businessInvestmentsDisburse) {
            controller.disburse(it)
        })
    )
}