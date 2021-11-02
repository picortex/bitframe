@file:OptIn(ExperimentalStdlibApi::class)

package cache

import cache.exceptions.CacheLoadException
import kotlinx.coroutines.delay
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import later.Later
import later.later
import later.then
import kotlin.js.JsExport
import kotlin.js.JsName

class BrowserCache(
    override val config: BrowserCacheConfig = BrowserCacheConfig()
) : Cache<String>(config) {
    private val storage = config.storage

    private val scope = config.scope

    private val json = config.json

    override val keys: Set<String>
        get() = buildSet {
            for (i in 0 until storage.length) add(storage.key(i) as String)
        }

    override fun <T> save(key: String, obj: T, serializer: KSerializer<T>) = scope.later {
        storage.setItem(key, json.encodeToString(serializer, obj))
        obj
    }

    override fun <T> load(key: String, serializer: KSerializer<T>): Later<T> = scope.later {
        val js = storage.getItem(key) ?: throw CacheLoadException("Object of with key=$key was not found in the cache")
        json.decodeFromString(serializer, js)
    }
}