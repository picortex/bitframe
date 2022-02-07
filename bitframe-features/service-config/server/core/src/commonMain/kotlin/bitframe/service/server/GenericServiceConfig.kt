package bitframe.service.server

import bitframe.daos.DaoFactory
import bitframe.service.server.config.ServiceConfig
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import logging.Logger
import kotlin.reflect.KClass

interface GenericServiceConfig<D : Any> : ServiceConfig {
    val clazz: KClass<D>

    companion object {
        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            daoFactory: DaoFactory,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
        ): GenericServiceConfig<D> = object : GenericServiceConfig<D>, ServiceConfig by ServiceConfig(daoFactory, bus, logger, scope) {
            override val clazz: KClass<D> = clazz
        }

        inline operator fun <reified D : Any> invoke(
            daoFactory: DaoFactory,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
        ): GenericServiceConfig<D> = invoke(D::class, daoFactory, bus, logger, scope)
    }
}