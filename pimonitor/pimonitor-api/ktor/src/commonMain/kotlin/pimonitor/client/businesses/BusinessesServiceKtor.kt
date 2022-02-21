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
import later.Later
import later.later
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateBusinessParams
import response.decodeResponseFromString

class BusinessesServiceKtor(
    override val config: KtorServiceConfig
) : BusinessesService(config) {
    private val baseUrl = "/api/businesses"
    val client get() = config.http
    val json get() = config.json

    @OptIn(InternalAPI::class)
    override fun create(rb: RequestBody.Authorized<CreateBusinessParams>) = config.scope.later {
        val req = client.post("${config.url}$baseUrl/create") {
            body = json.of(rb)
        }
        println(req.bodyAsText())
        json.decodeResponseFromString(CreateBusinessParams.serializer(), req.bodyAsText()).response()
    }

    @OptIn(InternalAPI::class)
    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val req = client.post("${config.url}$baseUrl/all") {
            body = json.of(rb)
        }
        val resp = json.decodeResponseFromString(ListSerializer(MonitoredBusinessSummary.serializer()), req.bodyAsText())
        resp.response().toInteroperableList()
    }
}