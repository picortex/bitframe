package bitframe.server

import bitframe.server.modules.GenericModule
import bitframe.server.modules.Module

interface BitframeApplicationConfig<out S : BitframeService> {
    val service: S
    val modules: MutableList<Module>

    companion object {
        fun <S : BitframeService> defaultModules(service: S) = mutableListOf(
            GenericModule(service.genericUsers),
            GenericModule(service.genericSpaces)
        )

        operator fun <S : BitframeService> invoke(
            service: S,
            modules: MutableList<Module> = mutableListOf(),
        ) = object : BitframeApplicationConfig<S> {
            override val service = service
            override val modules: MutableList<Module> = (modules + defaultModules(service)).toMutableList()
        }
    }
}