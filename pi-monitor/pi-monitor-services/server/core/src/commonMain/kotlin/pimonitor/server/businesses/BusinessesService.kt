package pimonitor.server.businesses

import later.Later
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.MonitorRef

class BusinessesService(
    override val config: BusinessesServiceConfig
) : BusinessesService(config) {

    override fun executeCreate(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef,
    ): Later<MonitoredBusiness> = dao.create(params, monitorRef)

    override fun all(): Later<List<MonitoredBusiness>> = dao.all()
}