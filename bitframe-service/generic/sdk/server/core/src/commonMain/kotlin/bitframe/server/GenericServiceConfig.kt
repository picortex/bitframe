package bitframe.server

import bitframe.core.DaoFactory
import bitframe.core.DaodServiceConfig
import bitframe.core.GenericDaodServiceConfig
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import logging.Logger
import kotlin.reflect.KClass

interface GenericServiceConfig<D : Any> : GenericDaodServiceConfig<D>, ServiceConfig {
    companion object {
        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            daoFactory: DaoFactory,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            json: Json = DaodServiceConfig.DEFAULT_JSON,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
        ): GenericServiceConfig<D> = object : GenericServiceConfig<D>, GenericDaodServiceConfig<D> by GenericDaodServiceConfig(clazz, daoFactory, bus, logger, json, scope) {}

        inline operator fun <reified D : Any> invoke(
            daoFactory: DaoFactory,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            json: Json = DaodServiceConfig.DEFAULT_JSON,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
        ): GenericServiceConfig<D> = invoke(D::class, daoFactory, bus, logger, json, scope)
    }
}