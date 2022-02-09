package bitframe.service.server

import bitframe.actors.modal.Savable
import bitframe.service.GenericService
import bitframe.service.daod.GenericDaodService

open class GenericService<T : Savable>(
    override val config: GenericServiceConfig<T>
) : GenericService<T> by GenericDaodService(config)