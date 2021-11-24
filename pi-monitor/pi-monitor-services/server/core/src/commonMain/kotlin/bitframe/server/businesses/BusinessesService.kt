package bitframe.server.businesses

import later.Later
import bitframe.evaluation.businesses.BusinessesService
import bitframe.monitored.CreateMonitoredBusinessParams
import bitframe.monitored.MonitoredBusiness
import bitframe.monitors.MonitorRef

class BusinessesService(
    override val config: BusinessesServiceConfig
) : BusinessesService(config) {

    private val dao = config.businessesDao
    
    override fun executeCreate(
        params: CreateMonitoredBusinessParams,
        monitorRef: MonitorRef,
    ): Later<MonitoredBusiness> = dao.create(params, monitorRef)

    override fun all(): Later<List<MonitoredBusiness>> = dao.all()
}