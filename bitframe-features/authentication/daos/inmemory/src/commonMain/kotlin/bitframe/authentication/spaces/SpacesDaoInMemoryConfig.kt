package bitframe.authentication.spaces

import bitframe.daos.config.MockDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex

interface SpacesDaoInMemoryConfig : MockDaoConfig {
    val spaces: MutableMap<String, Space>

    companion object {
        operator fun invoke(
            spaces: MutableMap<String, Space> = mutableMapOf(),
            simulationTime: Long = MockDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = MockDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = MockDaoConfig.DEFAULT_SCOPE,
        ): SpacesDaoInMemoryConfig = object : SpacesDaoInMemoryConfig, MockDaoConfig by MockDaoConfig(simulationTime, lock, scope) {
            override val spaces: MutableMap<String, Space> = spaces
        }
    }
}