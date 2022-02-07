package bitframe.service.server.config

import bitframe.daos.DaoFactory
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import logging.Logger

class ServiceConfigBuilder {
    var bus: EventBus = ServiceConfig.DEFAULT_BUS
    var logger: Logger = ServiceConfig.DEFAULT_LOGGER
    var scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE

    fun build(factory: DaoFactory): ServiceConfig = ServiceConfig(factory, bus, logger, scope)
}