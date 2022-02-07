package bitframe.server

import bitframe.actors.spaces.Space
import bitframe.actors.users.User
import bitframe.daos.DaoFactory
import bitframe.server.modules.Module
import bitframe.server.modules.ModuleConfiguration

interface BitframeApplicationConfig<out S : BitframeService> {
    val service: S
    val modules: MutableList<Module>

    companion object {
        fun defaultModules(daoFactory: DaoFactory) = mutableListOf(
            Module<User>(ModuleConfiguration(daoFactory)),
            Module<Space>(ModuleConfiguration(daoFactory))
        )

        operator fun <S : BitframeService> invoke(
            service: S,
            daoFactory: DaoFactory,
            module: MutableList<Module> = defaultModules(daoFactory),
        ) = object : BitframeApplicationConfig<S> {
            override val service = service
            override val modules: MutableList<Module> = module
        }
    }
}