package bitframe

import bitframe.internal.ServiceConfigDaodImpl
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import mailer.Mailer
import kotlin.jvm.*

val SERVICE_CONFIG_DAO_DEFAULT: ServiceConfigDaod = ServiceConfigDaodImpl

private val DEFAULT = SERVICE_CONFIG_DAO_DEFAULT

@JvmOverloads
fun ServiceConfigDaod(
    database: DaoFactory = DEFAULT.database,
    bus: EventBus = DEFAULT.bus,
    logger: Logger = DEFAULT.logger,
    mailer: Mailer = DEFAULT.mailer,
    codec: StringFormat = DEFAULT.codec,
    executor: Executor = DEFAULT.executor
): ServiceConfigDaod = ServiceConfigDaodImpl(bus, logger, codec, database, mailer, executor)