package pimonitor.monitors

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope

interface MonitorDaoInMemoryConfig : InMemoryDaoConfig {
    val monitors: MutableMap<String, Monitor>

    companion object {
        operator fun invoke(
            monitors: MutableMap<String, Monitor> = mutableMapOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULTATION_TIME,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE,
        ) = object : MonitorDaoInMemoryConfig {
            override val monitors: MutableMap<String, Monitor> = monitors
            override val simulationTime: Long = simulationTime
            override val scope: CoroutineScope = scope
        }
    }
}