package bitframe.api.internal

import bitframe.ApiConfigKtor
import bitframe.Session
import cache.Cache
import events.EventBus
import io.ktor.client.HttpClient
import koncurrent.CoroutineExecutor
import koncurrent.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.StringFormat
import live.MutableLive
import logging.Logger

class ApiConfigKtorImpl<E>(
    override val appId: String,
    override val session: MutableLive<Session>,
    override val cache: Cache,
    override val bus: EventBus,
    override val logger: Logger,
    override val executor: CoroutineExecutor,
    override val http: HttpClient,
    override val endpoint: E,
    override val codec: StringFormat
) : ApiConfigKtor<E>