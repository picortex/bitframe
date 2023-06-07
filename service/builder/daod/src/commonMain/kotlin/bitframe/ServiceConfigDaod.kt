package bitframe

import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import lexi.Logger
import mailer.Mailer

interface ServiceConfigDaod {
    val database: DaoFactory
    val bus: EventBus
    val logger: Logger
    val codec: StringFormat
    val mailer: Mailer
    val executor: Executor
}