package bitframe

import bitframe.server.BitframeApplicationConfig
import bitframe.server.BitframeApplicationConfig.Companion.DEFAULT_MODULES
import bitframe.server.BitframeService
import bitframe.server.modules.Module
import bitframe.server.modules.ModuleConfiguration
import java.io.File

interface ApplicationConfig<out S : BitframeService> : BitframeApplicationConfig<S> {
    val client: File

    companion object {
        operator fun <S : BitframeService> invoke(
            client: File,
            service: S,
            module: MutableList<Module> = mutableListOf(),
        ): ApplicationConfig<S> = object : ApplicationConfig<S>, BitframeApplicationConfig<S> by BitframeApplicationConfig(service, (module + DEFAULT_MODULES).toMutableList()) {
            override val client: File = client
        }
    }
}