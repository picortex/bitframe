package pimonitor.monitored

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.delay
import later.Later
import later.later
import pimonitor.monitors.MonitorRef

class MonitoredBusinessDaoInMemory(
    private val monitoredBusinesses: MutableMap<String, MonitoredBusiness> = mutableMapOf(),
    private val config: InMemoryDaoConfig
) : MonitoredBusinessDao {
    private val scope = config.scope

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