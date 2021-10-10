package pimonitor.evaluation.businesses

import bitframe.service.config.KtorClientConfiguration
import later.Later
import pimonitor.monitored.MonitoredBusiness

class BusinessServiceKtor(
    private val config: KtorClientConfiguration
) : BusinessService() {
    override fun all(): Later<List<MonitoredBusiness>> {
        TODO()
    }
}