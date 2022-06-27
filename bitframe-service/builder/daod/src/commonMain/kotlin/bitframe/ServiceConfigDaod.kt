package bitframe

import bitframe.service.internal.ServiceConfigDaodImpl
import events.EventBus
import events.InMemoryEventBus
import koncurrent.Executor
import koncurrent.Executors
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import logging.ConsoleAppender
import logging.Logger
import mailer.Mailer
import mailer.MockMailer
import kotlin.jvm.*

interface ServiceConfigDaod {
    val bus: EventBus
    val logger: Logger
    val codec: StringFormat
    val database: DaoFactory
    val mailer: Mailer
    val executor: Executor

    companion object {

        @JvmField
        val DEFAULT_BUS: EventBus = InMemoryEventBus()

        @JvmField
        val DEFAULT_LOGGER: Logger = Logger(ConsoleAppender())

        @JvmField
        val DEFAULT_MAILER: Mailer = MockMailer()

        @JvmField
        val DEFAULT_EXECUTOR: Executor = Executors.default()

        @JvmField
        val DEFAULT_CODEC: StringFormat = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }

        @JvmOverloads
        @JvmName("create")
        @JvmSynthetic
        operator fun invoke(
            database: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            mailer: Mailer = DEFAULT_MAILER,
            codec: StringFormat = DEFAULT_CODEC,
            executor: Executor = DEFAULT_EXECUTOR
        ): ServiceConfigDaod = ServiceConfigDaodImpl(bus, logger, codec, database, mailer, executor)
    }
}