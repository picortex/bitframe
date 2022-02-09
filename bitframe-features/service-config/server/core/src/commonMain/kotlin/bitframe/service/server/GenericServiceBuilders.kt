package bitframe.service.server

import bitframe.actors.modal.Savable
import bitframe.service.server.config.ServiceConfig
import bitframe.service.server.config.toGenericServiceConfig

inline fun <reified T : Savable> GenericService(config: ServiceConfig): GenericService<T> = GenericService(config.toGenericServiceConfig())