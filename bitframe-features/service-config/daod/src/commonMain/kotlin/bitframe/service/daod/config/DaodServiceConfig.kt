package bitframe.service.daod.config

import bitframe.daos.DaoFactory
import bitframe.service.config.ServiceConfig
import events.EventBus
import events.InMemoryEventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import logging.ConsoleAppender
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface DaodServiceConfig : ServiceConfig {
    val daoFactory: DaoFactory

    companion object {

        @JvmField
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())

        @JvmField
        val DEFAULT_BUS = InMemoryEventBus()

        @JvmField
        val DEFAULT_LOGGER = Logger(ConsoleAppender())

        @JvmSynthetic
        operator fun invoke(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): DaodServiceConfig = object : DaodServiceConfig {
            override val daoFactory: DaoFactory = daoFactory
            override val bus = bus
            override val logger: Logger = logger
            override val scope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(daoFactory, bus, logger, scope)
    }
}