package bitframe.server

import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import bitframe.server.modules.Module
import kotlin.jvm.JvmField

interface BitframeApplicationConfig<out S : BitframeService> {
    val service: S
    val modules: MutableList<Module>

    companion object {
        @JvmField
        val DEFAULT_MODULES = mutableListOf(
            Module<User>(),
            Module<Space>()
        )

        operator fun <S : BitframeService> invoke(
            service: S,
            module: MutableList<Module> = DEFAULT_MODULES,
        ) = object : BitframeApplicationConfig<S> {
            override val service = service
            override val modules: MutableList<Module> = module
        }
    }
}