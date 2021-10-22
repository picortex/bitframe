package pimonitor.evaluation.businesses

import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitored.MonitoredBusinessDao
import pimonitor.monitors.MonitorRef
import pimonitor.monitors.MonitorsService

class BusinessServiceImpl(
    override val bus: EventBus,
    private val dao: MonitoredBusinessDao,
    override val monitorsService: MonitorsService,
    override val config: ServiceConfig,
) : BusinessesService(bus,monitorsService,config) {

    override fun executeCreate(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef,
    ): Later<MonitoredBusiness> = dao.create(params, monitorRef)

    override fun all(): Later<List<MonitoredBusiness>> = dao.all()
}