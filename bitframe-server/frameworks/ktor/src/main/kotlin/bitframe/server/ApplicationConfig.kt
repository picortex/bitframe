package bitframe.server

import java.io.File

interface ApplicationConfig<out S : BitframeService> : BitframeApplicationConfig<S> {
    val client: File

    companion object {
        operator fun <S : BitframeService> invoke(
            client: File,
            service: S,
            modules: MutableList<Module> = mutableListOf(),
        ): ApplicationConfig<S> = object : ApplicationConfig<S>, BitframeApplicationConfig<S> by BitframeApplicationConfig(service, modules) {
            override val client: File = client
        }
    }
}