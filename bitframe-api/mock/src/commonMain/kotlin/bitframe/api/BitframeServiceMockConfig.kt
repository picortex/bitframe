package bitframe.api

import bitframe.daos.DaoFactory
import bitframe.service.Session
import bitframe.service.client.config.MockServiceConfig
import cache.Cache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface BitframeServiceMockConfig : BitframeServiceConfig, MockServiceConfig {
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

        @JvmSynthetic
        operator fun invoke(
            appId: String = MockServiceConfig.DEFAULT_APP_ID,
            cache: Cache = MockServiceConfig.DEFAULT_CACHE,
            session: MutableLive<Session> = MockServiceConfig.DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = MockServiceConfig.DEFAULT_DAO_FACTORY,
            bus: EventBus = MockServiceConfig.DEFAULT_BUS,
            logger: Logger = MockServiceConfig.DEFAULT_LOGGER,
            scope: CoroutineScope = MockServiceConfig.DEFAULT_SCOPE
        ): BitframeServiceMockConfig = object : BitframeServiceMockConfig, MockServiceConfig by MockServiceConfig(appId, cache, session, daoFactory, bus, logger, scope) {}

        @JvmStatic
        @JvmOverloads
        fun create(
            appId: String = MockServiceConfig.DEFAULT_APP_ID,
            cache: Cache = MockServiceConfig.DEFAULT_CACHE,
            session: MutableLive<Session> = MockServiceConfig.DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = MockServiceConfig.DEFAULT_DAO_FACTORY,
            bus: EventBus = MockServiceConfig.DEFAULT_BUS,
            logger: Logger = MockServiceConfig.DEFAULT_LOGGER,
            scope: CoroutineScope = MockServiceConfig.DEFAULT_SCOPE
        ): BitframeServiceMockConfig = invoke(appId, cache, session, daoFactory, bus, logger, scope)
    }
}