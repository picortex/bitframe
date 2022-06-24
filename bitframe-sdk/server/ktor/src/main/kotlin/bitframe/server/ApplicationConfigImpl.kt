package bitframe.server

internal class ApplicationConfigImpl<out S>(
    override val service: S,
    override val modules: MutableList<Module> = mutableListOf(),
) : ApplicationConfig<S>, BitframeApplicationConfig<S> by BitframeApplicationConfig(service, modules)