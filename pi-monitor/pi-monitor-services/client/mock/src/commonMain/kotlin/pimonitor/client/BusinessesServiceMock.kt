package pimonitor.client

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import later.Later
import later.later
import pimonitor.client.evaluation.businesses.BusinessesService
import pimonitor.client.evaluation.businesses.BusinessesServiceConfig
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.MonitorRef

class BusinessesServiceMock(override val config: BusinessesServiceConfig) : BusinessesService(config) {
    override fun all(): Later<List<MonitoredBusiness>> = scope.later {
        listOf()
    }

    override fun executeCreate(params: CreateMonitoredBusinessParams, monitorRef: MonitorRef): Later<MonitoredBusiness> {
        TODO("Not yet implemented")
    }
}