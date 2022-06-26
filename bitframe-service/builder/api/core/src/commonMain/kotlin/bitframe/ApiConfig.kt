@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe

import cache.Cache
import events.EventBus
import events.InMemoryEventBus
import koncurrent.Executor
import koncurrent.Executors
import live.MutableLive
import logging.ConsoleAppender
import logging.Logger
import kotlin.js.JsExport
import kotlin.jvm.JvmField

@JsExport
interface ApiConfig {
    val appId: String
    val session: MutableLive<Session>
    val cache: Cache
    val bus: EventBus
    val logger: Logger
    val executor: Executor

    companion object {

        @JvmField
        val DEFAULT_EXECUTOR: Executor = Executors.default()

        @JvmField
        val DEFAULT_BUS: EventBus = InMemoryEventBus()

        @JvmField
        val DEFAULT_LOGGER = Logger(ConsoleAppender())
    }
}