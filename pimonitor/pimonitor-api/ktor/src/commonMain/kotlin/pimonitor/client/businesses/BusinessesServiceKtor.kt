package pimonitor.client.businesses

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.builtins.nullable
import later.Later
import later.later
import pimonitor.client.utils.path
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.dashboards.OperationalDashboard
import response.decodeResponseFromString

class BusinessesServiceKtor(
    override val config: ServiceConfigKtor
) : BusinessesService(config) {
    private val path = config.path
    val client get() = config.http
    val json get() = config.json

    override fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>) = config.scope.later {
        val req = client.post(path.businessesCreate) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(CreateMonitoredBusinessResult.serializer(), req.bodyAsText()).response()
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val req = client.post(path.businessesAll) {
            setBody(json.of(rb))
        }
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

    override fun operationalDashboard(rb: RequestBody.Authorized<String>): Later<OperationalDashboard?> = config.scope.later {
        val req = client.post(path.businessesDashboardOperational) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(OperationalDashboard.serializer().nullable, req.bodyAsText()).response()
    }
}