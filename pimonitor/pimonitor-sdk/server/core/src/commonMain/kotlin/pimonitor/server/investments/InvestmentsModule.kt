package pimonitor.server.investments

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class InvestmentsModule(private val controller: InvestmentsController) : Module {
    private val path get() = controller.service.config.pathV1
    override val name: String = "Investments"
    override val actions: List<Action> = listOf(
        Action("Create Investment", mapOf(), HttpRoute(HttpMethod.Post, path.investmentsCreate) {
            controller.createInvestment(it)
        }),
        Action("Update Investment", mapOf(), HttpRoute(HttpMethod.Post, path.investmentsUpdate) {
            controller.updateInvestment(it)
        }),
        Action("Load all", mapOf(), HttpRoute(HttpMethod.Post, path.investmentsAll) {
            controller.all(it)
        }),
        Action("Create Disbursement", mapOf(), HttpRoute(HttpMethod.Post, path.investmentsDisbursementCreate) {
            controller.createDisbursement(it)
        }),
        Action("Update Disbursement", mapOf(), HttpRoute(HttpMethod.Post, path.investmentsDisbursementUpdate) {
            controller.updateInvestment(it)
        }),
        Action("Delete investment(s)", mapOf(), HttpRoute(HttpMethod.Post, path.investmentsDelete) {
            controller.deleteInvestment(it)
        })
    )
}