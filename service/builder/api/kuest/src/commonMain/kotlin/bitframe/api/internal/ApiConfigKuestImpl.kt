package bitframe.api.internal

import bitframe.ApiConfigKuest
import bitframe.Session
import cache.Cache
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kuest.HttpClient
import live.MutableLive
import logging.Logger

class ApiConfigKuestImpl<out E>(
    override val appId: String,
    override val session: MutableLive<Session>,
    override val cache: Cache,
    override val bus: EventBus,
    override val logger: Logger,
    override val executor: Executor,
    override val http: HttpClient,
    override val endpoint: E,
    override val codec: StringFormat
) : ApiConfigKuest<E>