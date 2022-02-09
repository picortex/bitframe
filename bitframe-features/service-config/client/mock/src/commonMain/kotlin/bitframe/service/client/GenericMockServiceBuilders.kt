package bitframe.service.client

import bitframe.actors.modal.Savable
import bitframe.service.GenericService
import bitframe.service.client.config.GenericMockServiceConfig
import bitframe.service.daod.GenericDaodService

inline fun <reified T : Savable> GenericMockService(
    config: GenericMockServiceConfig<T>? = null
): GenericMockService<T> = GenericMockService(config ?: GenericMockServiceConfig())