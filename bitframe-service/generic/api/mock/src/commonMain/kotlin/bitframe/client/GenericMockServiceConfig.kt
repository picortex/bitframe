package bitframe.client

import bitframe.core.DaoFactory
import bitframe.core.Session
import bitframe.core.DaodServiceConfig
import bitframe.core.GenericDaodServiceConfig
import cache.Cache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.reflect.KClass

interface GenericMockServiceConfig<T : Any> : GenericDaodServiceConfig<T>, MockServiceConfig {
    companion object {
        @JvmField
        val DEFAULT_APP_ID = MockServiceConfig.DEFAULT_APP_ID

        @JvmField
        val DEFAULT_DAO_FACTORY = MockServiceConfig.DEFAULT_DAO_FACTORY

        @JvmField
        val DEFAULT_BUS = DaodServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_CACHE = MockServiceConfig.DEFAULT_CACHE

        @JvmField
        val DEFAULT_LIVE_SESSION = MockServiceConfig.DEFAULT_LIVE_SESSION

        @JvmField
        val DEFAULT_LOGGER = DaodServiceConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_JSON = DaodServiceConfig.DEFAULT_JSON

        @JvmField
        val DEFAULT_SCOPE = DaodServiceConfig.DEFAULT_SCOPE

        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            appId: String = DEFAULT_APP_ID,
            cache: Cache = DEFAULT_CACHE,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = DEFAULT_DAO_FACTORY,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): GenericMockServiceConfig<D> = object : GenericMockServiceConfig<D>, MockServiceConfig by MockServiceConfig(appId, cache, session, daoFactory, bus, logger, json, scope) {
            override val clazz: KClass<D> = clazz
        }

        inline operator fun <reified D : Any> invoke(
            appId: String = DEFAULT_APP_ID,
            cache: Cache = DEFAULT_CACHE,
            session: MutableLive<Session> = DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = DEFAULT_DAO_FACTORY,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): GenericMockServiceConfig<D> = invoke(D::class, appId, cache, session, daoFactory, bus, logger, json, scope)
    }
}