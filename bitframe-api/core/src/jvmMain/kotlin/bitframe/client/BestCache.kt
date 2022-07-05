package bitframe.client

import cache.Cache
import cache.CacheMockConfig
import cache.CacheMock
import kotlinx.serialization.StringFormat
import java.util.concurrent.Executor

actual fun BestCache(namespace: String, codec: StringFormat, executor: Executor): Cache = CacheMock(
    CacheMockConfig(namespace = namespace, executor = executor)
)