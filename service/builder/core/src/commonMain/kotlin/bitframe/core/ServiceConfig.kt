@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.core

import events.EventBus
import events.InMemoryEventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import lexi.ConsoleAppender
import lexi.Logger
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

@Deprecated("In favour of bitframe.ApiConfig")
@JsExport
interface ServiceConfig {
    val bus: EventBus
    val logger: Logger
    val scope: CoroutineScope

    companion object {

        @JvmField
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())

        @JvmField
        val DEFAULT_BUS = InMemoryEventBus()

        @JvmField
        val DEFAULT_LOGGER = Logger(ConsoleAppender())

        @JvmSynthetic
        operator fun invoke(
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = ServiceConfigImpl(bus, logger, scope)

        @JvmOverloads
        @JvmStatic
        fun create(
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = ServiceConfigImpl(bus, logger, scope)
    }
}