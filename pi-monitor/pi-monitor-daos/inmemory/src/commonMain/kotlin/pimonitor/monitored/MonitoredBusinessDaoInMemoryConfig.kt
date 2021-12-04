package pimonitor.monitored

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import pimonitor.monitored.MonitoredBusiness

interface MonitoredBusinessDaoInMemoryConfig : InMemoryDaoConfig {
    val businesses: MutableMap<String, MonitoredBusiness>

    companion object {
        operator fun invoke(
            businesses: MutableMap<String, MonitoredBusiness> = mutableMapOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = InMemoryDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE
        ): MonitoredBusinessDaoInMemoryConfig = object : MonitoredBusinessDaoInMemoryConfig,
            InMemoryDaoConfig by InMemoryDaoConfig(simulationTime, lock, scope) {
            override val businesses: MutableMap<String, MonitoredBusiness> = businesses
        }
    }
}