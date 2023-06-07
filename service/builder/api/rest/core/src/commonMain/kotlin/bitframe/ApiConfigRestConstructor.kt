package bitframe

import bitframe.internal.ApiConfigRestImpl
import keep.Cache
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import cinematic.MutableLive
import lexi.Logger

val API_CONFIG_REST_DEFAULT: ApiConfigRest<Any, Any> = ApiConfigRestImpl

inline fun <E, H> ApiConfigRest(
    appId: String = API_CONFIG_REST_DEFAULT.appId,
    session: MutableLive<Session> = API_CONFIG_REST_DEFAULT.session,
    cache: Cache = API_CONFIG_REST_DEFAULT.cache,
    bus: EventBus = API_CONFIG_REST_DEFAULT.bus,
    logger: Logger = API_CONFIG_REST_DEFAULT.logger,
    executor: Executor = API_CONFIG_REST_DEFAULT.executor,
    endpoint: E,
    codec: StringFormat = API_CONFIG_REST_DEFAULT.codec,
    http: H,
): ApiConfigRest<E, H> = ApiConfigRestImpl(appId, session, cache, bus, logger, executor, endpoint, codec, http)