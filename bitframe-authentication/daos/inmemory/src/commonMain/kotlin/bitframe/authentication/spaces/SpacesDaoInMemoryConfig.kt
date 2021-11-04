package bitframe.authentication.spaces

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope

interface SpacesDaoInMemoryConfig : InMemoryDaoConfig {
    val spaces: MutableMap<String, Space>

    companion object {
        operator fun invoke(
            spaces: MutableMap<String, Space> = mutableMapOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULTATION_TIME,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE,
        ) = object : SpacesDaoInMemoryConfig {
            override val spaces: MutableMap<String, Space> = spaces
            override val simulationTime: Long = simulationTime
            override val scope: CoroutineScope = scope
        }
    }
}