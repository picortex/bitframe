package bitframe.service.client

import bitframe.actors.modal.Savable
import bitframe.service.GenericService
import bitframe.service.client.config.GenericMockServiceConfig
import bitframe.service.daod.GenericDaodService

class GenericMockService<T : Savable>(
    override val config: GenericMockServiceConfig<T>
) : GenericService<T> by GenericDaodService(config)