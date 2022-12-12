package bitframe

import bitframe.internal.ServiceConfigDaodImpl
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import mailer.Mailer
import kotlin.jvm.*

interface ServiceConfigDaod {
    val database: DaoFactory
    val bus: EventBus
    val logger: Logger
    val codec: StringFormat
    val mailer: Mailer
    val executor: Executor
}