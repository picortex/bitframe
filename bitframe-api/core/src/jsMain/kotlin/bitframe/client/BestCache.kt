package bitframe.client

import cache.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import platform.Platform

actual fun BestCache(namespace: String, json: Json, scope: CoroutineScope): Cache = when {
    Platform.isBrowser -> BrowserCache(
        BrowserCacheConfig(namespace = namespace, json = json, scope = scope)
    )
    Platform.isReactNative -> AsyncStorageCache(
        AsyncStorageCacheConfig(namespace = namespace, json = json, scope = scope)
    )
    else -> MockCache(
        MockCacheConfig(namespace = namespace, scope = scope)
    )
}