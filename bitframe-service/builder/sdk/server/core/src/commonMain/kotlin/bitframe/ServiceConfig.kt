package bitframe

import bitframe.core.ServiceConfigDaod
import bitframe.core.DaoFactory
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import logging.Logger
import mailer.Mailer
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface ServiceConfig : ServiceConfigDaod {
    companion object {
        @JvmField
        val DEFAULT_SCOPE = ServiceConfigDaod.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = ServiceConfigDaod.DEFAULT_BUS

        @JvmField
        val DEFAULT_LOGGER = ServiceConfigDaod.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_MAILER = ServiceConfigDaod.DEFAULT_MAILER

        @JvmField
        val DEFAULT_JSON = ServiceConfigDaod.DEFAULT_JSON

        @JvmSynthetic
        operator fun invoke(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            mailer: Mailer = DEFAULT_MAILER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = object : ServiceConfig, ServiceConfigDaod by ServiceConfigDaod(daoFactory, bus, logger, mailer, json, scope) {}

        @JvmOverloads
        @JvmStatic
        fun create(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            mailer: Mailer = DEFAULT_MAILER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(daoFactory, bus, logger, mailer, json, scope)
    }
}