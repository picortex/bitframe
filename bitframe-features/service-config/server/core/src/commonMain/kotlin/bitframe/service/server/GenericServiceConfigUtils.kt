package bitframe.service.server

import bitframe.service.server.config.ServiceConfig

inline fun <reified T : Any> ServiceConfig.toGenericServiceConfig() = GenericServiceConfig(T::class, daoFactory, bus, logger, scope)