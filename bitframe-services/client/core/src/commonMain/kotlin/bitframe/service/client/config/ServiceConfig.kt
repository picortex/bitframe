package bitframe.service.client.config

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
    val appId: String
    val cache: Cache

    companion object {
        @JvmField
        val DEFAULT_SCOPE = CoreServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = CoreServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_LOGGER = CoreServiceConfig.DEFAULT_LOGGER

        @JvmSynthetic
        operator fun invoke(
            appId: String,
            cache: Cache,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = object : ServiceConfig, CoreServiceConfig by CoreServiceConfig(bus, logger, scope) {
            override val appId = appId
            override val cache = cache
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            appId: String,
            cache: Cache,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(appId, cache, bus, logger, scope)
    }
}