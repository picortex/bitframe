package bitframe.service.server.config

import bitframe.service.server.GenericServiceConfig

inline fun <reified T : Any> ServiceConfig.toGenericServiceConfig() = GenericServiceConfig(
    T::class, daoFactory, bus, logger, scope
)