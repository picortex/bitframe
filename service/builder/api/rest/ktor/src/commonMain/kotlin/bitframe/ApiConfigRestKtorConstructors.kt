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

inline fun <E> ApiConfigRestKtor(
    appId: String = API_CONFIG_REST_KTOR_DEFAULT.appId,
    session: MutableLive<Session> = API_CONFIG_REST_KTOR_DEFAULT.session,
    cache: Cache = API_CONFIG_REST_KTOR_DEFAULT.cache,
    bus: EventBus = API_CONFIG_REST_KTOR_DEFAULT.bus,
    logger: Logger = API_CONFIG_REST_KTOR_DEFAULT.logger,
    executor: CoroutineExecutor = API_CONFIG_REST_KTOR_DEFAULT.executor,
    http: HttpClient = API_CONFIG_REST_KTOR_DEFAULT.http,
    endpoint: E,
    codec: StringFormat = API_CONFIG_REST_KTOR_DEFAULT.codec,
): ApiConfigRestKtor<E> = ApiConfigRestKtorImpl(appId, session, cache, bus, logger, executor, http, endpoint, codec)