package bitframe.server

import java.io.File

interface ApplicationConfig<out S> : BitframeApplicationConfig<S> {
    val client: File

    companion object {
        operator fun <S> invoke(
            client: File,
            service: S,
            modules: MutableList<Module> = mutableListOf(),
        ): ApplicationConfig<S> = ApplicationConfigImpl(client, service, modules)
    }
}