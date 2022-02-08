package pimonitor.server.businesses

import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import kotlinx.collections.interoperable.List
import kotlinx.coroutines.launch
import later.Later
import later.await
import later.later
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitored.toMonitoredBusiness
import pimonitor.monitors.MonitorRef

class BusinessesService(
    override val config: ServiceConfig
) : BusinessesService(config) {

    private val dao = config.daoFactory.get<MonitoredBusiness>()

    override fun executeCreate(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef,
    ): Later<out MonitoredBusiness> = dao.create(
        params.toMonitoredBusiness("", monitorRef)
    )

    override fun all(): Later<out List<MonitoredBusiness>> = dao.all()
}