package pimonitor.monitored

import kotlinx.coroutines.delay
import later.Later
import later.later
import pimonitor.monitors.MonitorRef
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitored.MonitoredBusinessesDao
import pimonitor.monitored.toMonitoredBusiness

class MonitoredBusinessDaoInMemory(
    val config: MonitoredBusinessDaoInMemoryConfig = MonitoredBusinessDaoInMemoryConfig()
) : MonitoredBusinessesDao {
    private val scope = config.scope
    private val monitoredBusinesses = config.businesses

    override fun create(params: CreateMonitoredBusinessParams, monitorRef: MonitorRef) = scope.later {
        delay(config.simulationTime)
        val uid = "${monitoredBusinesses.size + 1}"
        val bus = params.toMonitoredBusiness(uid, monitorRef)
        monitoredBusinesses[uid] = bus
        bus
    }

    override fun all(): Later<List<MonitoredBusiness>> = scope.later {
        delay(config.simulationTime)
        monitoredBusinesses.values.toList()
    }
}