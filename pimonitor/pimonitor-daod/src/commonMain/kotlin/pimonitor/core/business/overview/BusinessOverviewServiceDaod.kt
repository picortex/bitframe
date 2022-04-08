package pimonitor.core.business.overview

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import later.Later
import pimonitor.core.business.utils.info.LoadInfoParsedParams

class BusinessOverviewServiceDaod(
    val config: ServiceConfigDaod
) : BusinessOverviewServiceCore {
    override fun load(params: RequestBody.Authorized<LoadInfoParsedParams>): Later<MonitoredBusinessOverview> {
        TODO("Not yet implemented")
    }
}