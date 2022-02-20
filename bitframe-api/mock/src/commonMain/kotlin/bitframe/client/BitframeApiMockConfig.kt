package bitframe.client

import bitframe.core.DaoFactory
import bitframe.core.DaodServiceConfig
import bitframe.core.Session
import cache.Cache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface BitframeApiMockConfig : BitframeApiConfig, MockServiceConfig {
    companion object {
        @JvmField
        val DEFAULT_APP_ID = MockServiceConfig.DEFAULT_APP_ID

        @JvmField
        val DEFAULT_SCOPE = MockServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_LIVE_SESSION = MockServiceConfig.DEFAULT_LIVE_SESSION

        @JvmField
        val DEFAULT_LOGGER = MockServiceConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_CACHE = MockServiceConfig.DEFAULT_CACHE

        @JvmField
        val DEFAULT_BUS = MockServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_DAO_FACTORY = MockServiceConfig.DEFAULT_DAO_FACTORY

        @JvmField
        val DEFAULT_JSON = MockServiceConfig.DEFAULT_JSON

        @JvmSynthetic
        operator fun invoke(
            appId: String = DEFAULT_APP_ID,
            cache: Cache = DEFAULT_CACHE,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = DEFAULT_DAO_FACTORY,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): BitframeApiMockConfig = object : BitframeApiMockConfig, MockServiceConfig by MockServiceConfig(appId, cache, session, daoFactory, bus, logger, json, scope) {}

        @JvmStatic
        @JvmOverloads
        fun create(
            appId: String = DEFAULT_APP_ID,
            cache: Cache = DEFAULT_CACHE,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = DEFAULT_DAO_FACTORY,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): BitframeApiMockConfig = invoke(appId, cache, session, daoFactory, bus, logger, json, scope)
    }
}