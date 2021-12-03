package bitframe.authentication.spaces

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex

interface SpacesDaoInMemoryConfig : InMemoryDaoConfig {
    val spaces: MutableMap<String, Space>

    companion object {
        operator fun invoke(
            spaces: MutableMap<String, Space> = mutableMapOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = InMemoryDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE,
        ): SpacesDaoInMemoryConfig = object : SpacesDaoInMemoryConfig, InMemoryDaoConfig by InMemoryDaoConfig(simulationTime, lock, scope) {
            override val spaces: MutableMap<String, Space> = spaces
        }
    }
}