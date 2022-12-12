package bitframe

import bitframe.internal.ApiConfigRestImpl
import cache.Cache
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import live.MutableLive
import logging.Logger

val API_CONFIG_REST_DEFAULT: ApiConfigRest<Any, Any> = ApiConfigRestImpl

@PublishedApi
internal val DEFAULT = API_CONFIG_REST_DEFAULT

inline fun <E, H> ApiConfigRest(
    appId: String = DEFAULT.appId,
    session: MutableLive<Session> = DEFAULT.session,
    cache: Cache = DEFAULT.cache,
    bus: EventBus = DEFAULT.bus,
    logger: Logger = DEFAULT.logger,
    executor: Executor = DEFAULT.executor,
    endpoint: E,
    codec: StringFormat = DEFAULT.codec,
    http: H,
): ApiConfigRest<E, H> = ApiConfigRestImpl(appId, session, cache, bus, logger, executor, endpoint, codec, http)