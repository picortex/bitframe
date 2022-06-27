package bitframe

import bitframe.api.internal.ApiConfigKuestImpl
import cache.Cache
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kuest.HttpClient
import live.MutableLive
import live.mutableLiveOf
import logging.Logger
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

interface ApiConfigKuest<out E> : ApiConfig, ApiConfigRest<E, HttpClient> {
    override val http: HttpClient

    companion object {
        @JvmStatic
        @JvmOverloads
        @JvmName("create")
        operator fun <E> invoke(
            appId: String,
            endpoint: E,
            http: HttpClient,
            cache: Cache,
            bus: EventBus = ApiConfig.DEFAULT_BUS,
            logger: Logger = ApiConfig.DEFAULT_LOGGER,
            executor: Executor = ApiConfig.DEFAULT_EXECUTOR,
            codec: StringFormat = ApiConfigRest.DEFAULT_CODEC,
            session: MutableLive<Session> = mutableLiveOf(Session.Unknown)
        ): ApiConfigKuest<E> = ApiConfigKuestImpl(
            appId, session, cache, bus, logger, executor, http, endpoint, codec
        )
    }
}