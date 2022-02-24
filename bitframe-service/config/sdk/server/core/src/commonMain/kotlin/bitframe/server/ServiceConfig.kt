package bitframe.server

import bitframe.core.DaoFactory
import bitframe.core.DaodServiceConfig
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface ServiceConfig : DaodServiceConfig {
    companion object {
        @JvmField
        val DEFAULT_SCOPE = DaodServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = DaodServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_LOGGER = DaodServiceConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_JSON = DaodServiceConfig.DEFAULT_JSON

        @JvmSynthetic
        operator fun invoke(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = object : ServiceConfig, DaodServiceConfig by DaodServiceConfig(daoFactory, bus, logger, json, scope) {}

        @JvmOverloads
        @JvmStatic
        fun create(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(daoFactory, bus, logger, json, scope)
    }
}