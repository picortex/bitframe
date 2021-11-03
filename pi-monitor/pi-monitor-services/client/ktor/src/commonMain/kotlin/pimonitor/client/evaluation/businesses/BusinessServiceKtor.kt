package pimonitor.client.evaluation.businesses

import later.Later
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.MonitorRef

class BusinessServiceKtor(
    override val config: BusinessServiceKtorConfig,
) : BusinessesService(config) {
    override fun executeCreate(params: CreateMonitoredBusinessParams, monitorRef: MonitorRef): Later<MonitoredBusiness> {
        TODO("Not yet implemented")
    }

    override fun all(): Later<List<MonitoredBusiness>> {
        TODO()
    }
}