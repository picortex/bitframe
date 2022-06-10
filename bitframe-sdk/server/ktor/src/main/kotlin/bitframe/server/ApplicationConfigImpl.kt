package bitframe.server

import java.io.File

internal class ApplicationConfigImpl<out S>(
    override val client: File,
    override val service: S,
    override val modules: MutableList<Module> = mutableListOf(),
) : ApplicationConfig<S>, BitframeApplicationConfig<S> by BitframeApplicationConfig(service, modules)