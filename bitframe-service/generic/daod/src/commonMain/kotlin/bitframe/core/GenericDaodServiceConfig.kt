package bitframe.core

import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import logging.Logger
import mailer.Mailer
import kotlin.jvm.JvmField
import kotlin.reflect.KClass

interface GenericDaodServiceConfig<D : Any> : ServiceConfigDaod {
    val clazz: KClass<D>

    companion object {
        @JvmField
        val DEFAULT_BUS = ServiceConfigDaod.DEFAULT_BUS

        @JvmField
        val DEFAULT_LOGGER = ServiceConfigDaod.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_MAILER = ServiceConfigDaod.DEFAULT_MAILER

        @JvmField
        val DEFAULT_SCOPE = ServiceConfigDaod.DEFAULT_SCOPE

        @JvmField
        val DEFAULT_JSON = ServiceConfigDaod.DEFAULT_JSON

        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            mailer: Mailer = DEFAULT_MAILER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): GenericDaodServiceConfig<D> = object : GenericDaodServiceConfig<D>, ServiceConfigDaod by ServiceConfigDaod(daoFactory, bus, logger, mailer, json, scope) {
            override val clazz: KClass<D> = clazz
        }

        inline operator fun <reified D : Any> invoke(
            daoFactory: DaoFactory,
            bus: EventBus = DEFAULT_BUS,
            logger: Logger = DEFAULT_LOGGER,
            mailer: Mailer = DEFAULT_MAILER,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): GenericDaodServiceConfig<D> = invoke(D::class, daoFactory, bus, logger, mailer, json, scope)
    }
}