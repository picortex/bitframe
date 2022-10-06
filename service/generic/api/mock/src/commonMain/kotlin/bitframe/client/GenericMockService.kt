package bitframe.client

import bitframe.core.Savable
import bitframe.core.GenericService
import bitframe.core.GenericDaodService

class GenericMockService<T : Savable>(
    override val config: GenericMockServiceConfig<T>
) : GenericService<T> by GenericDaodService(config)