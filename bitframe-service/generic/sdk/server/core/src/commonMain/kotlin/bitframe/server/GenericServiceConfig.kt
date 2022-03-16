package bitframe.server

import bitframe.core.DaoFactory
import bitframe.core.ServiceConfigDaod
import bitframe.core.GenericDaodServiceConfig
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import logging.Logger
import mailer.Mailer
import kotlin.reflect.KClass

interface GenericServiceConfig<D : Any> : GenericDaodServiceConfig<D>, ServiceConfig {
    companion object {
        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            daoFactory: DaoFactory,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            mailer: Mailer = ServiceConfig.DEFAULT_MAILER,
            json: Json = ServiceConfigDaod.DEFAULT_JSON,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
        ): GenericServiceConfig<D> = object : GenericServiceConfig<D>, GenericDaodServiceConfig<D> by GenericDaodServiceConfig(clazz, daoFactory, bus, logger, mailer, json, scope) {}

        inline operator fun <reified D : Any> invoke(
            daoFactory: DaoFactory,
            bus: EventBus = ServiceConfig.DEFAULT_BUS,
            logger: Logger = ServiceConfig.DEFAULT_LOGGER,
            mailer: Mailer = ServiceConfig.DEFAULT_MAILER,
            json: Json = ServiceConfigDaod.DEFAULT_JSON,
            scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
        ): GenericServiceConfig<D> = invoke(D::class, daoFactory, bus, logger, mailer, json, scope)
    }
}