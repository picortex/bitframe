package pimonitor.server.business.financials

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class BusinessFinancialsModule(
    private val controller: BusinessFinancialsController
) : Module {
    private val path = controller.service.config.pathV1
    override val name = "Business Financials"
    override val actions: List<Action> = listOf(
        Action("load available reports for business", mapOf(), HttpRoute(HttpMethod.Post, path.businessFinancialReportsAvailable) {
            controller.availableReports(it)
        }),
        Action("income statements", mapOf(), HttpRoute(HttpMethod.Post, path.businessFinancialReportIncomeStatement) {
            controller.incomeStatement(it)
        }),
        Action("balance sheet", mapOf(), HttpRoute(HttpMethod.Post, path.businessFinancialReportBalanceSheet) {
            controller.balanceSheet(it)
        })
    )
}