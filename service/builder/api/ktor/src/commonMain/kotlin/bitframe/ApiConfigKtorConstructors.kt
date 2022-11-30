package bitframe

import bitframe.api.internal.ApiConfigKtorImpl
import cache.Cache
import events.EventBus
import io.ktor.client.*
import koncurrent.CoroutineExecutor
import kotlinx.serialization.StringFormat
import live.mutableLiveOf
import logging.Logger

fun <E> ApiConfigKtor(
    appId: String,
    cache: Cache,
    endpoint: E,
    executor: CoroutineExecutor = ApiConfigKtor.DEFAULT_EXECUTOR,
    bus: EventBus = ApiConfig.DEFAULT_BUS,
    logger: Logger = ApiConfig.DEFAULT_LOGGER,
    http: HttpClient = ApiConfigKtor.DEFAULT_HTTP_CLIENT,
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