package bitframe.internal

import bitframe.DaoFactory
import bitframe.DaoFactoryMock
import bitframe.ServiceConfigDaod
import events.EventBus
import events.InMemoryEventBus
import koncurrent.Executor
import koncurrent.Executors
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import lexi.ConsoleAppender
import lexi.Logger
import mailer.Mailer
import mailer.MockMailer

@PublishedApi
internal class ServiceConfigDaodImpl(
    override val bus: EventBus,
    override val logger: Logger,
    override val codec: StringFormat,
    override val database: DaoFactory,
    override val mailer: Mailer,
    override val executor: Executor
) : ServiceConfigDaod {
    companion object Default : ServiceConfigDaod by ServiceConfigDaodImpl(
        bus = InMemoryEventBus(),
        logger = Logger(ConsoleAppender()),
        codec = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        },
        database = DaoFactoryMock(),
        mailer = MockMailer(),
        executor = Executors.default()
    )
}