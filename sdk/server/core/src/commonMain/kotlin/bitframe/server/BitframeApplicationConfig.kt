package bitframe.server

import bitframe.Module
import kollections.MutableList
import kollections.iMutableListOf

interface BitframeApplicationConfig<out S> {
    val service: S
    val modules: MutableList<Module>

    companion object {
        fun defaultModules() = iMutableListOf<Module>()

        operator fun <S> invoke(
            service: S,
            modules: MutableList<Module> = defaultModules(),
        ): BitframeApplicationConfig<S> = BitframeApplicationConfigImpl(service, modules)
    }
}