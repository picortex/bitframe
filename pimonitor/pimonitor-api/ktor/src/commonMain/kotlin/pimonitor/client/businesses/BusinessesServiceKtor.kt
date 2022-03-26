package pimonitor.client.businesses

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.business.params.LoadReportParams
import pimonitor.core.business.params.LoadReportRawParams
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.businesses.results.AvailableReportsResults
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import response.decodeResponseFromString

class BusinessesServiceKtor(
    val config: ServiceConfigKtor
) : BusinessesService(config) {
    private val path = config.pathV1
    private val client get() = config.http
    private val json get() = config.json

    override fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>) = config.scope.later {
        val req = client.post(path.businessesCreate) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(CreateMonitoredBusinessResult.serializer(), req.bodyAsText()).response()
    }

    override fun load(rb: RequestBody.Authorized<String>) = config.scope.later {
        val req = client.post(path.businessesLoad) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(MonitoredBusinessBasicInfo.serializer(), req.bodyAsText()).response()
    }

    override fun availableReports(rb: RequestBody.Authorized<String>) = config.scope.later {
        val req = client.post(path.businessesAvailableReports) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(AvailableReportsResults.serializer(), req.bodyAsText()).response()
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val req = client.post(path.businessesAll) {
            setBody(json.of(rb))
        }
        println(req.bodyAsText())
        val resp = json.decodeResponseFromString(ListSerializer(MonitoredBusinessSummary.serializer()), req.bodyAsText())
        resp.response().toInteroperableList()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>) = config.scope.later {
        val req = client.post(path.businessesDelete) {
            setBody(json.of(rb))
        }
        val resp = json.decodeResponseFromString(ListSerializer(MonitoredBusinessBasicInfo.serializer()), req.bodyAsText())
        resp.response().toInteroperableList()
    }

    override fun operationalDashboard(rb: RequestBody.Authorized<LoadReportParams>) = config.scope.later {
        val req = client.post(path.businessesDashboardOperational) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(InfoResults.serializer(OperationalDifferenceBoard.serializer()), req.bodyAsText()).response()
    }

    override fun incomeStatement(rb: RequestBody.Authorized<String>) = config.scope.later {
        val req = client.post(path.businessesIncomeStatement) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(InfoResults.serializer(IncomeStatement.serializer()), req.bodyAsText()).response()
    }

    override fun balanceSheet(rb: RequestBody.Authorized<String>) = config.scope.later {
        val req = client.post(path.businessesBalanceSheet) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(InfoResults.serializer(BalanceSheet.serializer()), req.bodyAsText()).response()
    }
}