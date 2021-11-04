package bitframe.daos

import bitframe.daos.config.InMemoryDaoConfig
import kotlinx.coroutines.CoroutineScope

interface InMemoryGenericDaoConfig<D> : InMemoryDaoConfig {
    val items: MutableList<D>

    companion object {
        operator fun <D> invoke(
            items: MutableList<D> = mutableListOf(),
            simulationTime: Long = InMemoryDaoConfig.DEFAULT_SIMULTATION_TIME,
            scope: CoroutineScope = InMemoryDaoConfig.DEFAULT_SCOPE
        ): InMemoryGenericDaoConfig<D> = object : InMemoryGenericDaoConfig<D> {
            override val scope: CoroutineScope = scope
            override val items: MutableList<D> = items
            override val simulationTime: Long = simulationTime
        }
    }
}