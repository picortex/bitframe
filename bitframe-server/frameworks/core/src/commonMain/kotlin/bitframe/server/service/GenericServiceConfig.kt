package bitframe.server.service

import bitframe.daos.DaoFactory
import bitframe.service.server.config.ServiceConfig
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import logging.Logger
import kotlin.reflect.KClass

interface GenericServiceConfig<T : Any> : ServiceConfig {
    val clazz: KClass<T>

    companion object {
        operator fun <T : Any> invoke(
            daoFactory: DaoFactory,
            clazz: KClass<T>,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
        ) = object : GenericServiceConfig<T> {
            override val bus: EventBus = bus
            override val logger: Logger = logger
            override val scope: CoroutineScope = scope
            override val daoFactory: DaoFactory = daoFactory
            override val clazz: KClass<T> = clazz
        }
    }
}