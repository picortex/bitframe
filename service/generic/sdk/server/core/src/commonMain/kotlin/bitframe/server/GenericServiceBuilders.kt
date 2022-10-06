package bitframe.server

import bitframe.core.Savable

inline fun <reified T : Savable> GenericService(config: ServiceConfig): GenericService<T> = GenericService(config.toGenericServiceConfig())