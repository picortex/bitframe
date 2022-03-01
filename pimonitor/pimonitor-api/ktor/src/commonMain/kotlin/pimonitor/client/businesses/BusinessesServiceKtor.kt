package pimonitor.client.businesses

import bitframe.client.KtorServiceConfig
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.builtins.serializer
import later.Later
import later.later
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import response.decodeResponseFromString

class BusinessesServiceKtor(
    override val config: KtorServiceConfig
) : BusinessesService(config) {
    private val baseUrl = "/api/businesses"
    val client get() = config.http
    val json get() = config.json

    override fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>) = config.scope.later {
        val req = client.post("${config.url}$baseUrl/create") {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(CreateMonitoredBusinessParams.serializer(), req.bodyAsText()).response()
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val req = client.post("${config.url}$baseUrl/all") {
            setBody(json.of(rb))
        }
        val resp = json.decodeResponseFromString(ListSerializer(MonitoredBusinessSummary.serializer()), req.bodyAsText())
        resp.response().toInteroperableList()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>) = config.scope.later {
        val req = client.post("${config.url}$baseUrl/delete") {
            setBody(json.of(rb))
        }
        val resp = json.decodeResponseFromString(ListSerializer(MonitoredBusinessBasicInfo.serializer()), req.bodyAsText())
        resp.response().toInteroperableList()
    }
}