package bitframe.client

import bitframe.core.DaoFactory
import bitframe.core.Session
import cache.Cache
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

interface BitframeApiMockConfig : BitframeApiConfig, ServiceConfigMock {
    companion object {
        @JvmField
        val DEFAULT_APP_ID = ServiceConfigMock.DEFAULT_APP_ID

        @JvmField
        val DEFAULT_SCOPE = ServiceConfigMock.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_LIVE_SESSION = ServiceConfigMock.DEFAULT_LIVE_SESSION

        @JvmField
        val DEFAULT_LOGGER = ServiceConfigMock.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_MAILER = ServiceConfigMock.DEFAULT_MAILER

        @JvmField
        val DEFAULT_CACHE = ServiceConfigMock.DEFAULT_CACHE

        @JvmField
        val DEFAULT_BUS = ServiceConfigMock.DEFAULT_BUS

        @JvmField
        val DEFAULT_DAO_FACTORY = ServiceConfigMock.DEFAULT_DAO_FACTORY

        @JvmField
        val DEFAULT_JSON = ServiceConfigMock.DEFAULT_JSON

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
        ): BitframeApiMockConfig = object : BitframeApiMockConfig, ServiceConfigMock by ServiceConfigMock(appId, cache, session, daoFactory, bus, logger, mailer, json, scope) {}

        @JvmStatic
        @JvmOverloads
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
        ): BitframeApiMockConfig = invoke(appId, cache, session, daoFactory, bus, logger, mailer, json, scope)
    }
}