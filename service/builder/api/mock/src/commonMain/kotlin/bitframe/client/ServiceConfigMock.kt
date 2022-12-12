package bitframe.client

import bitframe.core.DaoFactory
import bitframe.core.ServiceConfigDaod
import bitframe.core.MockDaoFactory
import bitframe.core.Session
import cache.Cache
import cache.CacheMock
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger
import mailer.Mailer
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

@Deprecated("In favour of bitframe.ApiConfigMock")
interface ServiceConfigMock : ServiceConfig, ServiceConfigDaod {
    companion object {
        @JvmField
        val DEFAULT_APP_ID = "<mock-app>"

        @JvmField
        val DEFAULT_CACHE = CacheMock()

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

        @JvmField
        val DEFAULT_MAILER = ServiceConfigDaod.DEFAULT_MAILER

        @JvmField
        val DEFAULT_JSON = ServiceConfigDaod.DEFAULT_JSON

        @JvmSynthetic
        operator fun invoke(
            appId: String = DEFAULT_APP_ID,
            cache: Cache = DEFAULT_CACHE,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = DEFAULT_DAO_FACTORY,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            mailer: Mailer = DEFAULT_MAILER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfigMock = object : ServiceConfigMock {
            override val daoFactory: DaoFactory = daoFactory
            override val appId: String = appId
            override val session: MutableLive<Session> = session
            override val cache: Cache = cache
            override val bus = bus
            override val mailer = mailer
            override val logger: Logger = logger
            override val json = json
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
            mailer: Mailer = DEFAULT_MAILER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(appId, cache, session, daoFactory, bus, logger, mailer, json, scope)
    }
}