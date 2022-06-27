package bitframe.internal

import bitframe.ApplicationConfig
import bitframe.Module

class ApplicationConfigImpl<out S>(
    override val service: S,
    override val modules: List<Module>
) : ApplicationConfig<S>