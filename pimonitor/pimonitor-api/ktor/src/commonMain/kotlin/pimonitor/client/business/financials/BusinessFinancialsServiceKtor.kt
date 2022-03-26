package pimonitor.client.business.financials

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.businesses.results.AvailableReportsResults
import pimonitor.core.invites.InfoResults
import response.decodeResponseFromString

class BusinessFinancialsServiceKtor(
    private val config: ServiceConfigKtor,
) : BusinessFinancialsService(config) {

    private val client get() = config.http
    private val json get() = config.json
    private val path get() = config.pathV1

    override fun availableReports(rb: RequestBody.Authorized<String>) = config.scope.later {
        val req = client.post(path.businessFinancialReportsAvailable) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(AvailableReportsResults.serializer(), req.bodyAsText()).response()
    }

    override fun incomeStatement(rb: RequestBody.Authorized<String>) = config.scope.later {
        val req = client.post(path.businessFinancialReportIncomeStatement) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(InfoResults.serializer(IncomeStatement.serializer()), req.bodyAsText()).response()
    }

    override fun balanceSheet(rb: RequestBody.Authorized<String>) = config.scope.later {
        val req = client.post(path.businessFinancialReportBalanceSheet) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(InfoResults.serializer(BalanceSheet.serializer()), req.bodyAsText()).response()
    }
}