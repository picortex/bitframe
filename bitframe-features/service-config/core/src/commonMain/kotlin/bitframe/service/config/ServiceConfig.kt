package bitframe.service.config

import events.EventBus
import events.InMemoryEventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import logging.ConsoleAppender
import logging.Logger
import mailer.Mailer
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic
import kotlin.math.log

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
        ): ServiceConfig = object : ServiceConfig {
            override val bus = bus
            override val logger: Logger = logger
            override val scope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(bus, logger, scope)
    }
}