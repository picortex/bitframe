package bitframe.internal

import bitframe.API_CONFIG_DEFAULT
import bitframe.ApiConfigRest
import bitframe.Session
import keep.Cache
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import cinematic.MutableLive
import lexi.Logger

@PublishedApi
internal class ApiConfigRestImpl<out E, out H>(
    override val appId: String,
    override val session: MutableLive<Session>,
    override val cache: Cache,
    override val bus: EventBus,
    override val logger: Logger,
    override val executor: Executor,
    override val endpoint: E,
    override val codec: StringFormat,
    override val http: H
) : ApiConfigRest<E, H> {
    companion object Default : ApiConfigRest<Any, Any> by ApiConfigRestImpl(
        appId = API_CONFIG_DEFAULT.appId,
        session = API_CONFIG_DEFAULT.session,
        cache = API_CONFIG_DEFAULT.cache,
        bus = API_CONFIG_DEFAULT.bus,
        logger = API_CONFIG_DEFAULT.logger,
        executor = API_CONFIG_DEFAULT.executor,
        endpoint = Unit,
        codec = Json {
            ignoreUnknownKeys = true
            isLenient = true
        },
        http = Unit
    )

    override fun <E2> map(transform: (E) -> E2) = ApiConfigRestImpl(appId, session, cache, bus, logger, executor, transform(endpoint), codec, http)
}