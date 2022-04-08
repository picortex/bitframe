package pimonitor.core.business.overview

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import later.Later
import pimonitor.core.business.utils.info.LoadInfoParams

class BusinessOverviewServiceDaod(
    val config: ServiceConfigDaod
) : BusinessOverviewServiceCore {
    override fun load(params: RequestBody.Authorized<LoadInfoParams>): Later<MonitoredBusinessOverview> {
        TODO("Not yet implemented")
    }
}