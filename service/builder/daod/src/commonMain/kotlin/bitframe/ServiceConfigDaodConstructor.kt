package bitframe

import bitframe.internal.ServiceConfigDaodImpl
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import mailer.Mailer
import kotlin.jvm.*

val SERVICE_CONFIG_DAO_DEFAULT: ServiceConfigDaod = ServiceConfigDaodImpl

@JvmOverloads
fun ServiceConfigDaod(
    database: DaoFactory = SERVICE_CONFIG_DAO_DEFAULT.database,
    bus: EventBus = SERVICE_CONFIG_DAO_DEFAULT.bus,
    logger: Logger = SERVICE_CONFIG_DAO_DEFAULT.logger,
    mailer: Mailer = SERVICE_CONFIG_DAO_DEFAULT.mailer,
    codec: StringFormat = SERVICE_CONFIG_DAO_DEFAULT.codec,
    executor: Executor = SERVICE_CONFIG_DAO_DEFAULT.executor
): ServiceConfigDaod = ServiceConfigDaodImpl(bus, logger, codec, database, mailer, executor)