package bitframe.service.internal

import bitframe.DaoFactory
import bitframe.ServiceConfigDaod
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import mailer.Mailer

class ServiceConfigDaodImpl(
    override val bus: EventBus,
    override val logger: Logger,
    override val codec: StringFormat,
    override val database: DaoFactory,
    override val mailer: Mailer,
    override val executor: Executor
) : ServiceConfigDaod