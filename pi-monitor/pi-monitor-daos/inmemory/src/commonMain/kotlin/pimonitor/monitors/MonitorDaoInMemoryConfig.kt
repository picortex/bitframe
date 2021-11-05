package pimonitor.monitors

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex

interface MonitorDaoInMemoryConfig : InMemoryDaoConfig {
    val monitors: MutableMap<String, Monitor>

    companion object {
        operator fun invoke(
            monitors: MutableMap<String, Monitor> = mutableMapOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = InMemoryDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE,
        ): MonitorDaoInMemoryConfig = object : MonitorDaoInMemoryConfig, InMemoryDaoConfig by InMemoryDaoConfig(simulationTime, lock, scope) {
            override val monitors: MutableMap<String, Monitor> = monitors
        }
    }
}