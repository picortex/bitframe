package pimonitor.evaluation.businesses

import later.Later
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitored.MonitoredBusinessDao

class BusinessServiceImpl(
    private val dao: MonitoredBusinessDao,
) : BusinessesService() {
    override fun all(): Later<List<MonitoredBusiness>> = dao.all()
}