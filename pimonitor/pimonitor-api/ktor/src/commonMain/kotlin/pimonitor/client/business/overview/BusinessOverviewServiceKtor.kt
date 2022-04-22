package pimonitor.client.business.overview

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.Later
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.business.overview.MonitoredBusinessOverview
import pimonitor.core.business.utils.info.LoadInfoParsedParams
import response.decodeResponseFromString

class BusinessOverviewServiceKtor(
    private val config: ServiceConfigKtor
) : BusinessOverviewService(config) {
    private val json get() = config.json
    private val http get() = config.http
    private val path get() = config.pathV1
    override fun load(rb: RequestBody.Authorized<LoadInfoParsedParams>): Later<MonitoredBusinessOverview> = config.scope.later {
        val res = http.post(path.businessOverviewLoad) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(MonitoredBusinessOverview.serializer(), res.bodyAsText()).response()
    }
}