package pimonitor.monitored

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.delay
import later.Later
import later.later

class MonitoredBusinessDaoInMemory(
    private val monitoredBusinesses: MutableMap<String, MonitoredBusiness> = mutableMapOf(),
    private val config: InMemoryDaoConfig
) : MonitoredBusinessDao {
    private val scope = config.scope
    override fun all(): Later<List<MonitoredBusiness>> = scope.later {
        delay(config.simulationTime.toLong())
        monitoredBusinesses.values.toList()
    }
}