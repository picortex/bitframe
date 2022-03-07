package pimonitor.client.businesses

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import later.later
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.Invite
import response.decodeResponseFromString

class BusinessesServiceKtor(
    override val config: ServiceConfigKtor
) : BusinessesService(config) {
    private val baseUrl = "/api/businesses"
    val client get() = config.http
    val json get() = config.json

    override fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>) = config.scope.later {
        val req = client.post("${config.url}$baseUrl/create") {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(CreateMonitoredBusinessResult.serializer(), req.bodyAsText()).response()
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val req = client.post("${config.url}$baseUrl/all") {
            setBody(json.of(rb))
        }
        val resp = json.decodeResponseFromString(ListSerializer(MonitoredBusinessSummary.serializer()), req.bodyAsText())
        resp.response().toInteroperableList()
    }

    override fun invite(rb: RequestBody.Authorized<InviteToShareReportsParams>) = config.scope.later {
        val req = client.post("${config.url}$baseUrl/invite") {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Invite.serializer(), req.bodyAsText()).response()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>) = config.scope.later {
        val req = client.post("${config.url}$baseUrl/delete") {
            setBody(json.of(rb))
        }
        val resp = json.decodeResponseFromString(ListSerializer(MonitoredBusinessBasicInfo.serializer()), req.bodyAsText())
        resp.response().toInteroperableList()
    }
}