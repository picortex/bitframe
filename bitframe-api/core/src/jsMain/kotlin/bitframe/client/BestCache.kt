package bitframe.client

import cache.Cache
import cache.CacheBrowser
import cache.CacheBrowserConfig
import cache.CacheAsyncStorage
import cache.CacheAsyncStorageConfig
import cache.CacheMock
import cache.CacheMockConfig
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import platform.Platform

actual fun BestCache(namespace: String, codec: StringFormat, executor: Executor): Cache = when {
    Platform.isBrowser -> CacheBrowser(
        CacheBrowserConfig(namespace = namespace, codec = codec, executor = executor)
    )
    Platform.isReactNative -> CacheAsyncStorage(
        CacheAsyncStorageConfig(namespace = namespace, codec = codec, executor = executor)
    )
    else -> CacheMock(
        CacheMockConfig(namespace = namespace, executor = executor)
    )
}