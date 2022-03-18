package pimonitor.server.businesses

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.path

class BusinessModule(
    private val controller: BusinessController
) : Module {
    val path = controller.service.config.path
    override val name = "Businesses"
    override val actions: List<Action> = listOf(
        Action("create", mapOf(), HttpRoute(HttpMethod.Post, path.businessesCreate) {
            controller.create(it)
        }),
        Action("all", mapOf(), HttpRoute(HttpMethod.Post, path.businessesAll) {
            controller.all(it)
        }),
        Action("delete", mapOf(), HttpRoute(HttpMethod.Post, path.businessesDelete) {
            controller.delete(it)
        }),
        Action("operational dashboard", mapOf(), HttpRoute(HttpMethod.Post, path.businessesDashboardOperational) {
            controller.dashboard(it)
        }),
        Action("income statements", mapOf(), HttpRoute(HttpMethod.Post, path.businessesIncomeStatement) {
            controller.incomeStatement(it)
        }),
        Action("balance sheet", mapOf(), HttpRoute(HttpMethod.Post, path.businessesBalanceSheet) {
            controller.balanceSheet(it)
        }),
        Action("load business", mapOf(), HttpRoute(HttpMethod.Post, path.businessesLoad) {
            controller.load(it)
        }),
        Action("load available reports for business", mapOf(), HttpRoute(HttpMethod.Post, path.businessesAvailableReports) {
            controller.availableReports(it)
        })
    )
}