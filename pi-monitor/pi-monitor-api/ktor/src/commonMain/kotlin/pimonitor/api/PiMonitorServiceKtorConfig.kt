package pimonitor.api

import bitframe.api.BitframeServiceConfig
import bitframe.service.client.config.KtorClientConfiguration
import events.EventBus
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_BUS
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_HTTP_CLIENT
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_JSON
import bitframe.service.client.config.KtorClientConfiguration.Companion.DEFAULT_SCOPE
import bitframe.service.client.config.ServiceConfig
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmOverloads
import bitframe.service.Session as SignInSession

class PiMonitorServiceKtorConfig @JvmOverloads constructor(
    override val appId: String,
    override val url: String,
    override val cache: Cache,
    override val session: MutableLive<SignInSession> = ServiceConfig.DEFAULT_LIVE_SESSION,
    override val json: Json = DEFAULT_JSON,
    override val bus: EventBus = DEFAULT_BUS,
    override val logger: Logger = ServiceConfig.DEFAULT_LOGGER,
    override val http: HttpClient = DEFAULT_HTTP_CLIENT,
    override val scope: CoroutineScope = DEFAULT_SCOPE,
) : BitframeServiceConfig, KtorClientConfiguration