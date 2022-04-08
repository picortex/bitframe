package pimonitor.client.business.overview

import bitframe.client.ServiceConfigKtor
import bitframe.core.RequestBody
import later.Later
import later.later
import pimonitor.core.business.overview.MonitoredBusinessOverview
import pimonitor.core.business.utils.info.LoadInfoParsedParams

class BusinessOverviewServiceKtor(
    private val config: ServiceConfigKtor
) : BusinessOverviewService(config) {
    override fun load(params: RequestBody.Authorized<LoadInfoParsedParams>): Later<MonitoredBusinessOverview> = config.scope.later {
        TODO("Not yet implemented")
    }
}