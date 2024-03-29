package bitframe.client

import bitframe.core.DaoFactory
import bitframe.core.Session
import bitframe.core.ServiceConfigDaod
import bitframe.core.GenericDaodServiceConfig
import cache.Cache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger
import mailer.Mailer
import kotlin.jvm.JvmField
import kotlin.reflect.KClass

interface GenericMockServiceConfig<T : Any> : GenericDaodServiceConfig<T>, ServiceConfigMock {
    companion object {
        @JvmField
        val DEFAULT_APP_ID = ServiceConfigMock.DEFAULT_APP_ID

        @JvmField
        val DEFAULT_DAO_FACTORY = ServiceConfigMock.DEFAULT_DAO_FACTORY

        @JvmField
        val DEFAULT_BUS = ServiceConfigDaod.DEFAULT_BUS

        @JvmField
        val DEFAULT_CACHE = ServiceConfigMock.DEFAULT_CACHE

        @JvmField
        val DEFAULT_LIVE_SESSION = ServiceConfigMock.DEFAULT_LIVE_SESSION

        @JvmField
        val DEFAULT_LOGGER = ServiceConfigDaod.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_MAILER = ServiceConfigDaod.DEFAULT_MAILER

        @JvmField
        val DEFAULT_JSON = ServiceConfigDaod.DEFAULT_JSON

        @JvmField
        val DEFAULT_SCOPE = ServiceConfigDaod.DEFAULT_SCOPE

        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            appId: String = DEFAULT_APP_ID,
            cache: Cache = DEFAULT_CACHE,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = DEFAULT_DAO_FACTORY,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            mailer: Mailer = DEFAULT_MAILER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): GenericMockServiceConfig<D> = object : GenericMockServiceConfig<D>, ServiceConfigMock by ServiceConfigMock(appId, cache, session, daoFactory, bus, logger, mailer, json, scope) {
            override val clazz: KClass<D> = clazz
        }

        inline operator fun <reified D : Any> invoke(
            appId: String = DEFAULT_APP_ID,
            cache: Cache = DEFAULT_CACHE,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = DEFAULT_DAO_FACTORY,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            mailer: Mailer = DEFAULT_MAILER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): GenericMockServiceConfig<D> = invoke(D::class, appId, cache, session, daoFactory, bus, logger, mailer, json, scope)
    }
}