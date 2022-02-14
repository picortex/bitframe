package bitframe.client

import bitframe.core.Session
import cache.Cache
import events.EventBus
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger

interface BitframeApiKtorConfig : BitframeApiConfig, KtorServiceConfig {
    companion object {
        operator fun invoke(
            appId: String,
            url: String,
            cache: Cache,
            session: MutableLive<Session> = ServiceConfig.DEFAULT_LIVE_SESSION,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            http: HttpClient = KtorServiceConfig.DEFAULT_HTTP_CLIENT,
            json: Json = KtorServiceConfig.DEFAULT_JSON,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
        ) = object : BitframeApiKtorConfig {
            override val session: MutableLive<Session> = session
            override val appId: String = appId
            override val cache: Cache = cache
            override val bus: EventBus = bus
            override val logger: Logger = logger
            override val scope: CoroutineScope = scope
            override val url: String = url
            override val http: HttpClient = http
            override val json: Json = json
        }
    }
}