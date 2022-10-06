package bitframe.client

import bitframe.core.ServiceConfigRest
import bitframe.core.Session
import cache.Cache
import events.EventBus
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger

class ServiceConfigKtorImpl<out E>(
    override val appId: String,
    override val url: String,
    override val endpoint: E,
    override val cache: Cache,
    override val http: HttpClient = ServiceConfigKtor.DEFAULT_HTTP_CLIENT,
    override val session: MutableLive<Session> = ServiceConfig.DEFAULT_LIVE_SESSION,
    override val bus: EventBus = ServiceConfig.DEFAULT_BUS,
    override val logger: Logger = ServiceConfig.DEFAULT_LOGGER,
    override val scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
    override val json: Json = ServiceConfigRest.DEFAULT_JSON,
) : ServiceConfigKtor<E>