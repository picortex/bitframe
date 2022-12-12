package bitframe

import bitframe.internal.ApiConfigRestKtorImpl
import cache.Cache
import events.EventBus
import io.ktor.client.*
import koncurrent.CoroutineExecutor
import kotlinx.serialization.StringFormat
import live.MutableLive
import logging.Logger

val API_CONFIG_REST_KTOR_DEFAULT: ApiConfigRestKtor<Any> = ApiConfigRestKtorImpl

@PublishedApi
internal val DEFAULT = API_CONFIG_REST_KTOR_DEFAULT

inline fun <E> ApiConfigRestKtor(
    appId: String = DEFAULT.appId,
    session: MutableLive<Session> = DEFAULT.session,
    cache: Cache = DEFAULT.cache,
    bus: EventBus = DEFAULT.bus,
    logger: Logger = DEFAULT.logger,
    executor: CoroutineExecutor = DEFAULT.executor,
    http: HttpClient = DEFAULT.http,
    endpoint: E,
    codec: StringFormat = DEFAULT.codec,
): ApiConfigRestKtor<E> = ApiConfigRestKtorImpl(appId, session, cache, bus, logger, executor, http, endpoint, codec)