package bitframe.client

import cache.Cache
import cache.MockCache
import cache.MockCacheConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

actual fun BestCache(namespace: String, json: Json, scope: CoroutineScope): Cache = MockCache(
    MockCacheConfig(namespace, scope = scope)
)