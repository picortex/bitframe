package bitframe

import keep.Cache
import events.EventBus
import koncurrent.Executor
import cinematic.MutableLive
import lexi.Logger

interface ApiConfig {
    val session: MutableLive<Session>
    val cache: Cache
    val bus: EventBus
    val logger: Logger
    val executor: Executor
    val appId: String
}