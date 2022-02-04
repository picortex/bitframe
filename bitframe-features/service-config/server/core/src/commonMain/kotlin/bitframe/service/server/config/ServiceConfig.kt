package bitframe.service.server.config

import bitframe.daos.DaoFactory
import events.EventBus
import cache.Cache
import kotlinx.coroutines.CoroutineScope
import logging.ConsoleAppender
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic
import bitframe.service.config.ServiceConfig as CoreServiceConfig

interface ServiceConfig : CoreServiceConfig {
    val daoFactory: DaoFactory

    companion object {
        @JvmField
        val DEFAULT_SCOPE = CoreServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = CoreServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_LOGGER = CoreServiceConfig.DEFAULT_LOGGER

        @JvmSynthetic
        operator fun invoke(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = object : ServiceConfig, CoreServiceConfig by CoreServiceConfig(bus, logger, scope) {
            override val daoFactory: DaoFactory = daoFactory
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