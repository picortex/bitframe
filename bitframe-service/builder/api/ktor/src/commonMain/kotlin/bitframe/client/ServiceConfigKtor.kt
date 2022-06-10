package bitframe.client

import bitframe.core.ServiceConfigRest
import bitframe.core.Session
import events.EventBus
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface ServiceConfigKtor<out E> : ServiceConfig, ServiceConfigRest<E> {
    val url: String
    val http: HttpClient

    companion object {
        @JvmField
        val DEFAULT_HTTP_CLIENT = HttpClient {
            expectSuccess = false
        }

        @JvmField
        val DEFAULT_SCOPE = ServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = ServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_LOGGER = ServiceConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_JSON = Json.Default

        @JvmSynthetic
        operator fun <E> invoke(
            url: String,
            appId: String,
            cache: Cache,
            endpoint: E,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            session: MutableLive<Session> = ServiceConfig.DEFAULT_LIVE_SESSION,
            http: HttpClient = DEFAULT_HTTP_CLIENT,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE,
        ): ServiceConfigKtor<E> = ServiceConfigKtorImpl(appId, url, endpoint, cache, http, session, bus, logger, scope, json)

        @JvmStatic
        @JvmOverloads
        fun <E> create(
            url: String,
            appId: String,
            cache: Cache,
            endpoint: E,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            session: MutableLive<Session> = ServiceConfig.DEFAULT_LIVE_SESSION,
            http: HttpClient = DEFAULT_HTTP_CLIENT,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE,
        ): ServiceConfigKtor<E> = ServiceConfigKtorImpl(appId, url, endpoint, cache, http, session, bus, logger, scope, json)
    }
}