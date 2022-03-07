package bitframe.core

import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import logging.Logger
import mailer.Mailer
import mailer.MockMailer
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface ServiceConfigDaod : ServiceConfig {
    val json: Json
    val daoFactory: DaoFactory
    val mailer: Mailer

    companion object {

        @JvmField
        val DEFAULT_SCOPE = ServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = ServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_LOGGER = ServiceConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_MAILER = MockMailer()

        @JvmField
        val DEFAULT_JSON = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }

        @JvmSynthetic
        operator fun invoke(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            mailer: Mailer = DEFAULT_MAILER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfigDaod = object : ServiceConfigDaod {
            override val daoFactory: DaoFactory = daoFactory
            override val bus = bus
            override val mailer = mailer
            override val logger: Logger = logger
            override val json: Json = json
            override val scope = scope
        }

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