package pimonitor.client.businesses

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.business.info.params.BusinessInfoParams
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
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

    override fun update(rb: RequestBody.Authorized<BusinessInfoParams>) = config.scope.later {
        val req = client.post(path.businessesUpdate) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(MonitoredBusinessBasicInfo.serializer(), req.bodyAsText()).response()
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
}