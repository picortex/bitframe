package bitframe.server

interface ApplicationConfig<out S> : BitframeApplicationConfig<S> {

    companion object {
        operator fun <S> invoke(
            service: S,
            modules: MutableList<Module> = mutableListOf(),
        ): ApplicationConfig<S> = ApplicationConfigImpl(service, modules)
    }
}