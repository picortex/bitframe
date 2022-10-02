package bitframe

import bitframe.internal.ApplicationConfigImpl

interface ApplicationConfig<out S> {
    val service: S
    val modules: List<Module>

    companion object {
        operator fun <S> invoke(
            service: S,
            modules: List<Module>
        ): ApplicationConfig<S> = ApplicationConfigImpl(service, modules)
    }
}