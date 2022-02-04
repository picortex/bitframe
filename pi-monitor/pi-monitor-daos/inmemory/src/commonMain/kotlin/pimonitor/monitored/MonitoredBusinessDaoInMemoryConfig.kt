package pimonitor.monitored

import bitframe.daos.config.MockDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex

interface MonitoredBusinessDaoInMemoryConfig : MockDaoConfig {
    val businesses: MutableMap<String, MonitoredBusiness>

    companion object {
        operator fun invoke(
            businesses: MutableMap<String, MonitoredBusiness> = mutableMapOf(),
            simulationTime: Long = MockDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = MockDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = MockDaoConfig.DEFAULT_SCOPE
        ): MonitoredBusinessDaoInMemoryConfig = object : MonitoredBusinessDaoInMemoryConfig,
            MockDaoConfig by MockDaoConfig(simulationTime, lock, scope) {
            override val businesses: MutableMap<String, MonitoredBusiness> = businesses
        }
    }
}