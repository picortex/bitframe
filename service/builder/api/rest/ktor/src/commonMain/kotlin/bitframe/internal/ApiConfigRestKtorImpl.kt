package bitframe.internal

import bitframe.API_CONFIG_REST_DEFAULT
import bitframe.ApiConfigRest
import bitframe.ApiConfigRestKtor
import bitframe.Session
import cache.Cache
import events.EventBus
import io.ktor.client.*
import koncurrent.CoroutineExecutor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.StringFormat
import live.MutableLive
import logging.Logger

@PublishedApi
internal class ApiConfigRestKtorImpl<E>(
    override val appId: String,
    override val session: MutableLive<Session>,
    override val cache: Cache,
    override val bus: EventBus,
    override val logger: Logger,
    override val executor: CoroutineExecutor,
    override val http: HttpClient,
    override val endpoint: E,
    override val codec: StringFormat
) : ApiConfigRestKtor<E> {
    override fun <E2> map(transform: (E) -> E2) = ApiConfigRestKtorImpl(appId, session, cache, bus, logger, executor, http, transform(endpoint), codec)

    companion object Default : ApiConfigRestKtor<Any> by ApiConfigRestKtorImpl(
        appId = API_CONFIG_REST_DEFAULT.appId,
        session = API_CONFIG_REST_DEFAULT.session,
        cache = API_CONFIG_REST_DEFAULT.cache,
        bus = API_CONFIG_REST_DEFAULT.bus,
        logger = API_CONFIG_REST_DEFAULT.logger,
        executor = CoroutineExecutor(CoroutineScope(Dispatchers.Default + SupervisorJob())),
        http = HttpClient {
            expectSuccess = false
        },
        endpoint = API_CONFIG_REST_DEFAULT.endpoint,
        codec = API_CONFIG_REST_DEFAULT.codec,
    )
}