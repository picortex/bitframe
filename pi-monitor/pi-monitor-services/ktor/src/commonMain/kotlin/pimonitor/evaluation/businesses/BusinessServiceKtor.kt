package pimonitor.evaluation.businesses

import bitframe.events.EventBus
import bitframe.service.client.config.KtorClientConfiguration
import later.Later
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.MonitorRef
import pimonitor.monitors.MonitorsService

class BusinessServiceKtor(
    override val bus: EventBus,
    override val monitorsService: MonitorsService,
    override val config: KtorClientConfiguration,
) : BusinessesService(bus, monitorsService, config) {
    override fun executeCreate(params: CreateMonitoredBusinessParams, monitorRef: MonitorRef): Later<MonitoredBusiness> {
        TODO("Not yet implemented")
    }

    override fun all(): Later<List<MonitoredBusiness>> {
        TODO()
    }
}