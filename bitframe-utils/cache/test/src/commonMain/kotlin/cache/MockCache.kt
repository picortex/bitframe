package cache

import cache.exceptions.CacheLoadException
import cache.exceptions.CacheMissException
import kotlinx.coroutines.delay
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import later.Later
import later.later
import later.then
import kotlin.js.JsExport
import kotlin.js.JsName

class MockCache<K : Any>(
    override val config: MockCacheConfig<K> = MockCacheConfig()
) : Cache<K>(config) {
    private val cache = config.initialCache

    private val scope get() = config.scope

    override fun keys(): Later<Set<K>> = scope.later {
        delay(config.simulationTime)
        cache.keys
    }

    override fun size(): Later<Int> = scope.later {
        delay(config.simulationTime)
        cache.size
    }

    val keys: Set<K> get() = cache.keys

    override fun <T> save(key: K, obj: T, serializer: KSerializer<T>) = scope.later {
        delay(config.simulationTime)
        cache[key] = obj
        obj
    }

    override fun <T> load(key: K, serializer: KSerializer<T>): Later<T> = scope.later {
        delay(config.simulationTime)
        val obj = cache[key] ?: throw CacheMissException(key)
        obj as T
    }
}