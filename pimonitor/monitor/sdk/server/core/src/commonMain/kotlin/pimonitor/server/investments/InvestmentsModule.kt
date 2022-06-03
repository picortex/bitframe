package pimonitor.server.investments

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentSummary
import pimonitor.server.disbursables.DisbursablesModule
import pimonitor.server.interventions.InterventionsController
import pimonitor.server.utils.pathV1

class InvestmentsModule(
    override val controller: InvestmentsController
) : DisbursablesModule<Investment, InvestmentSummary>(
    controller, Investment.serializer(), InvestmentSummary.serializer(), controller.service.config.pathV1.investments, "investment"
), Module {
    override val name: String = "Investments"
    override val actions: List<Action> = listOf(
        Action("Create Investment", mapOf(), HttpRoute(HttpMethod.Post, path.create) {
            controller.createInvestment(it)
        }),
        Action("Update Investment", mapOf(), HttpRoute(HttpMethod.Post, path.update) {
            controller.updateInvestment(it)
        })
    ) + disbursableActions
}