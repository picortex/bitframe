package pimonitor.client.businesses

import bitframe.client.KtorServiceConfig
import bitframe.core.RequestBody
import later.Later
import pimonitor.core.businesses.BusinessFilter
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateBusinessParams

class BusinessesServiceKtor(
    override val config: KtorServiceConfig
) : BusinessesService(config) {
    override fun create(rb: RequestBody.Authorized<CreateBusinessParams>): Later<CreateBusinessParams> {
        TODO("Not yet implemented")
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>): Later<List<MonitoredBusinessSummary>> {
        TODO("Not yet implemented")
    }
}