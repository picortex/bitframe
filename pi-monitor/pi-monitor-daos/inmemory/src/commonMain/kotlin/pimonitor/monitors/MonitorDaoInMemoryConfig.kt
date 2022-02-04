package pimonitor.monitors

import bitframe.daos.config.MockDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex

interface MonitorDaoInMemoryConfig : MockDaoConfig {
    val monitors: MutableMap<String, Monitor>

    companion object {
        operator fun invoke(
            monitors: MutableMap<String, Monitor> = mutableMapOf(),
            simulationTime: Long = MockDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = MockDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = MockDaoConfig.DEFAULT_SCOPE,
        ): MonitorDaoInMemoryConfig = object : MonitorDaoInMemoryConfig, MockDaoConfig by MockDaoConfig(simulationTime, lock, scope) {
            override val monitors: MutableMap<String, Monitor> = monitors
        }
    }
}