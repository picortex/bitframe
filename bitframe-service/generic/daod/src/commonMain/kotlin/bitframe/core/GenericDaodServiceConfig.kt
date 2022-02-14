package bitframe.core

import events.EventBus
import kotlinx.coroutines.CoroutineScope
import logging.Logger
import kotlin.jvm.JvmField
import kotlin.reflect.KClass

interface GenericDaodServiceConfig<D : Any> : DaodServiceConfig {
    val clazz: KClass<D>

    companion object {
        @JvmField
        val DEFAULT_BUS = DaodServiceConfig.DEFAULT_BUS

        @JvmField
        val DEFAULT_LOGGER = DaodServiceConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_SCOPE = DaodServiceConfig.DEFAULT_SCOPE

        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): GenericDaodServiceConfig<D> = object : GenericDaodServiceConfig<D>, DaodServiceConfig by DaodServiceConfig(daoFactory, bus, logger, scope) {
            override val clazz: KClass<D> = clazz
        }

        inline operator fun <reified D : Any> invoke(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): GenericDaodServiceConfig<D> = invoke(D::class, daoFactory, bus, logger, scope)
    }
}