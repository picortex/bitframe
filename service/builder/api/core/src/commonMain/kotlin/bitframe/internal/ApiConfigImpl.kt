package bitframe.internal

import bitframe.ApiConfig
import bitframe.Session
import keep.Cache
import keep.CacheMock
import events.EventBus
import events.InMemoryEventBus
import koncurrent.Executor
import koncurrent.Executors
import cinematic.MutableLive
import cinematic.mutableLiveOf
import lexi.ConsoleAppender
import lexi.Logger

@PublishedApi
internal class ApiConfigImpl private constructor(
    override val appId: String,
    override val session: MutableLive<Session>,
    override val cache: Cache,
    override val bus: EventBus,
    override val logger: Logger,
    override val executor: Executor
) : ApiConfig {
    companion object Default : ApiConfig by ApiConfigImpl(
        appId = "DEFAULT",
        session = mutableLiveOf(Session.Unknown),
        cache = CacheMock(),
        bus = InMemoryEventBus(),
        logger = Logger(ConsoleAppender()),
        executor = Executors.default()
    )
}