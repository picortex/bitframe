package pimonitor.client

import bitframe.client.BitframeApiConfig
import bitframe.client.KtorServiceConfig
import events.EventBus
import bitframe.client.KtorServiceConfig.Companion.DEFAULT_BUS
import bitframe.client.KtorServiceConfig.Companion.DEFAULT_HTTP_CLIENT
import bitframe.client.KtorServiceConfig.Companion.DEFAULT_JSON
import bitframe.client.KtorServiceConfig.Companion.DEFAULT_SCOPE
import bitframe.client.ServiceConfig
import bitframe.core.Session
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmOverloads

class PiMonitorApiKtorConfig @JvmOverloads constructor(
    override val appId: String,
    override val url: String,
    override val cache: Cache,
    override val session: MutableLive<Session> = ServiceConfig.DEFAULT_LIVE_SESSION,
    override val json: Json = DEFAULT_JSON,
    override val bus: EventBus = DEFAULT_BUS,
    override val logger: Logger = ServiceConfig.DEFAULT_LOGGER,
    override val http: HttpClient = DEFAULT_HTTP_CLIENT,
    override val scope: CoroutineScope = DEFAULT_SCOPE,
) : BitframeApiConfig, KtorServiceConfig