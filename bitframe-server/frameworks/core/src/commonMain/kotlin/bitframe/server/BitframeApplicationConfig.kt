package bitframe.server

import bitframe.server.modules.Module

interface BitframeApplicationConfig<out S : BitframeService> {
    val service: S
    val modules: MutableList<Module>

    companion object {
        operator fun <S : BitframeService> invoke(
            service: S,
            module: MutableList<Module> = mutableListOf(),
        ) = object : BitframeApplicationConfig<S> {
            override val service = service
            override val modules: MutableList<Module> = module
        }
    }
}