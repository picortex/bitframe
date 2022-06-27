package bitframe

import bitframe.api.internal.ApiConfigKtorImpl
import events.EventBus
import cache.Cache
import io.ktor.client.*
import koncurrent.CoroutineExecutor
import koncurrent.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import live.MutableLive
import live.mutableLiveOf
import logging.Logger
import kotlin.jvm.*

interface ApiConfigKtor<out E> : ApiConfig, ApiConfigRest<E, HttpClient> {
    override val http: HttpClient
    override val executor: CoroutineExecutor
    val scope: CoroutineScope get() = executor.scope

    companion object {
        @JvmField
        val DEFAULT_HTTP_CLIENT = HttpClient {
            expectSuccess = false
        }

        @JvmField
        val DEFAULT_SCOPE = CoroutineScope(Dispatchers.Default + SupervisorJob())

        @JvmField
        val DEFAULT_EXECUTOR = CoroutineExecutor(DEFAULT_SCOPE)

        @JvmOverloads
        @JvmName("create")
        @JvmSynthetic
        operator fun <E> invoke(
            appId: String,
            cache: Cache,
            endpoint: E,
            executor: CoroutineExecutor = DEFAULT_EXECUTOR,
            bus: EventBus = ApiConfig.DEFAULT_BUS,
            logger: Logger = ApiConfig.DEFAULT_LOGGER,
            http: HttpClient = DEFAULT_HTTP_CLIENT,
            codec: StringFormat = ApiConfigRest.DEFAULT_CODEC
        ): ApiConfigKtor<E> = ApiConfigKtorImpl(
            appId = appId,
            endpoint = endpoint,
            cache = cache,
            http = http,
            executor = executor,
            session = mutableLiveOf(Session.Unknown),
            bus = bus,
            logger = logger,
            codec = codec,
        )
    }
}