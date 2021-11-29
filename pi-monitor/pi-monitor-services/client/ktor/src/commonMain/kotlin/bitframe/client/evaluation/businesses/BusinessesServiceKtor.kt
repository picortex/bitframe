package bitframe.client.evaluation.businesses

import bitframe.response.response.decodeResponseFromString
import bitframe.service.client.utils.JsonContent
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.builtins.ListSerializer
import later.Later
import later.later
import bitframe.monitored.CreateBusinessRequestBody
import bitframe.monitored.CreateMonitoredBusinessParams
import bitframe.monitored.MonitoredBusiness
import bitframe.monitors.MonitorRef

class BusinessesServiceKtor(
    override val config: BusinessServiceKtorConfig,
) : BusinessesService(config) {

    private val http = config.http
    private val json = config.json
    private val url = config.url + "/api/businesses"

    private val serializer = MonitoredBusiness.serializer()

    override fun executeCreate(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef
    ): Later<MonitoredBusiness> = scope.later {
        val resp = try {
            val reqBody = CreateBusinessRequestBody(params, monitorRef)
            http.post(url) { body = JsonContent(reqBody) }
        } catch (exp: ClientRequestException) {
            exp.response
        }
        json.decodeResponseFromString(serializer, resp.readText()).response()
    }

    override fun all(): Later<List<MonitoredBusiness>> = scope.later {
        val resp = try {
            http.get(url)
        } catch (exp: ClientRequestException) {
            exp.response
        }
        json.decodeResponseFromString(ListSerializer(serializer), resp.readText()).response()
    }
}