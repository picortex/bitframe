package bitframe.server

import bitframe.core.GenericDaodService
import bitframe.core.Savable

open class GenericService<T : Savable>(
    override val config: GenericServiceConfig<T>
) : GenericDaodService<T>(config)