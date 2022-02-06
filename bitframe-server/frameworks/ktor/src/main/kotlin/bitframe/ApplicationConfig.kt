package bitframe

import bitframe.daos.DaoFactory
import bitframe.server.BitframeApplicationConfig
import bitframe.server.BitframeApplicationConfig.Companion.defaultModules
import bitframe.server.BitframeService
import bitframe.server.modules.Module
import java.io.File

interface ApplicationConfig<out S : BitframeService> : BitframeApplicationConfig<S> {
    val client: File

    companion object {
        operator fun <S : BitframeService> invoke(
            client: File,
            service: S,
            daoFactory: DaoFactory,
            module: MutableList<Module> = mutableListOf(),
        ): ApplicationConfig<S> = object : ApplicationConfig<S>, BitframeApplicationConfig<S> by BitframeApplicationConfig(service, daoFactory, (module + defaultModules(daoFactory)).toMutableList()) {
            override val client: File = client
        }
    }
}