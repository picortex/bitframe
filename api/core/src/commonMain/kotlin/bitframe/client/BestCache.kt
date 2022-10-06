package bitframe.client

import cache.Cache
import koncurrent.Executor
import kotlinx.serialization.StringFormat

expect fun BestCache(namespace: String, codec: StringFormat, executor: Executor): Cache