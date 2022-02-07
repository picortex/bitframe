package pimonitor.client.evaluation.businesses

import response.decodeResponseFromString
import bitframe.service.client.utils.JsonContent
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.builtins.ListSerializer
import later.Later
import later.later
import pimonitor.monitored.CreateBusinessRequestBody
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.MonitorRef

class BusinessesServiceKtor(
    override val config: BusinessServiceKtorConfig,
) : BusinessesService(config) {

    private val http = config.http
    private val json = config.json
    private val url = config.url + "/api/businesses"

    private val serializer = MonitoredBusiness.serializer()

    @OptIn(InternalAPI::class)
    override fun executeCreate(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef
    ): Later<MonitoredBusiness> = scope.later {
        val reqBody = CreateBusinessRequestBody(params, monitorRef)
        val resp = http.post(url) { body = JsonContent(reqBody) }
        json.decodeResponseFromString(serializer, resp.bodyAsText()).response()
    }

    override fun all(): Later<List<MonitoredBusiness>> = scope.later {
        val resp = http.get(url)
        json.decodeResponseFromString(ListSerializer(serializer), resp.bodyAsText()).response().toInteroperableList()
    }
}