package bitframe.client

import bitframe.core.DaoFactory
import bitframe.core.DaodServiceConfig
import bitframe.core.MockDaoFactory
import bitframe.core.Session
import cache.Cache
import cache.MockCache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface MockServiceConfig : ServiceConfig, DaodServiceConfig {
    companion object {
        @JvmField
        val DEFAULT_APP_ID = "<mock-app>"

        @JvmField
        val DEFAULT_CACHE = MockCache()

        @JvmField
        val DEFAULT_DAO_FACTORY = MockDaoFactory()

        @JvmField
        val DEFAULT_LIVE_SESSION = ServiceConfig.DEFAULT_LIVE_SESSION

        @JvmField
        val DEFAULT_SCOPE = ServiceConfig.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_BUS = ServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_LOGGER = ServiceConfig.DEFAULT_LOGGER

        @JvmSynthetic
        operator fun invoke(
            appId: String = DEFAULT_APP_ID,
            cache: Cache = DEFAULT_CACHE,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = DEFAULT_DAO_FACTORY,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): MockServiceConfig = object : MockServiceConfig {
            override val daoFactory: DaoFactory = daoFactory
            override val appId: String = appId
            override val session: MutableLive<Session> = session
            override val cache: Cache = cache
            override val bus = bus
            override val logger: Logger = logger
            override val scope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            appId: String = DEFAULT_APP_ID,
            cache: Cache = DEFAULT_CACHE,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = DEFAULT_DAO_FACTORY,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(appId, cache, session, daoFactory, bus, logger, scope)
    }
}