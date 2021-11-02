package cache

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

open class MockCacheConfig<K : Any>(
    val initialCache: MutableMap<K, Any?> = mutableMapOf(),
    val simulationTime: Long = 0L,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : CacheConfiguration(scope)