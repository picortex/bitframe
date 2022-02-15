package bitframe.client.configurators

import bitframe.client.BestCache
import cache.Cache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import logging.ConsoleAppender
import logging.Logger

sealed class ApiMode {
    abstract val appId: String
    abstract val namespace: String
    abstract val scope: CoroutineScope
    abstract val json: Json
    abstract val cache: Cache
    abstract val logger: Logger

    data class Mock(
        override val appId: String = "mock.app",
        override val namespace: String = "mock.app",
        override val json: Json = Json { prettyPrint = true },
        override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
        override val logger: Logger = Logger(ConsoleAppender()),
        override val cache: Cache = BestCache(namespace, json, scope)
    ) : ApiMode()

    data class Live(
        override val appId: String,
        val url: String,
        override val namespace: String = "app",
        override val json: Json = Json { prettyPrint = false },
        override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
        override val logger: Logger = Logger(ConsoleAppender()),
        override val cache: Cache = BestCache(namespace, json, scope)
    ) : ApiMode()
}