package pimonitor.core.configs

import akkounts.sage.SageOneZAService
import bitframe.core.DaoFactory
import bitframe.core.ServiceConfigDaod
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import logging.Logger
import mailer.Mailer
import pimonitor.core.picortex.PiCortexService

interface MonitorServiceConfigDaod : ServiceConfigDaod {
    val sage: SageOneZAService
    val picortex: PiCortexService

    companion object {
        operator fun invoke(
            daoFactory: DaoFactory,
            sage: SageOneZAService,
            picortex: PiCortexService,
            bus: EventBus = ServiceConfigDaod.DEFAULT_BUS,
            logger: Logger = ServiceConfigDaod.DEFAULT_LOGGER,
            mailer: Mailer = ServiceConfigDaod.DEFAULT_MAILER,
            json: Json = ServiceConfigDaod.DEFAULT_JSON,
            scope: CoroutineScope = ServiceConfigDaod.DEFAULT_SCOPE
        ): MonitorServiceConfigDaod = MonitorServiceConfigImpl(daoFactory, sage, picortex, bus, logger, mailer, json, scope)
    }
}