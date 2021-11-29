package bitframe.daos

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex

interface InMemoryGenericDaoConfig<D> : InMemoryDaoConfig {
    val items: MutableList<D>

    companion object {
        operator fun <D> invoke(
            items: MutableList<D> = mutableListOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = InMemoryDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE
        ): InMemoryGenericDaoConfig<D> = object : InMemoryGenericDaoConfig<D>, InMemoryDaoConfig by InMemoryDaoConfig(simulationTime, lock, scope) {
            override val items: MutableList<D> = items
        }
    }
}