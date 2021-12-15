package pimonitor.server.businesses

import kotlinx.coroutines.launch
import later.Later
import later.await
import later.later
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.MonitorRef

class BusinessesService(
    override val config: BusinessesServiceConfig
) : BusinessesService(config) {

    private val dao = config.businessesDao
    private val mailer get() = config.mailer

    override fun executeCreate(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef,
    ): Later<MonitoredBusiness> = dao.create(params, monitorRef)

    override fun all(): Later<List<MonitoredBusiness>> = dao.all()
}