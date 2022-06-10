package bitframe.server

import kotlinx.collections.interoperable.mutableListOf

interface BitframeApplicationConfig<out S> {
    val service: S
    val modules: MutableList<Module>

    companion object {
        fun defaultModules() = mutableListOf<Module>()

        operator fun <S> invoke(
            service: S,
            modules: MutableList<Module> = defaultModules(),
        ): BitframeApplicationConfig<S> = BitframeApplicationConfigImpl(service, modules)
    }
}