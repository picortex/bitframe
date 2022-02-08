package bitframe.server

import bitframe.server.modules.Module
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

interface KtorServerConfigurationBuilder<S : BitframeService> : ServerConfigurationBuilder<S> {
    var public: File?

    var moduleBuilders: MutableList<(S) -> Module>

    fun buildApplicationConfig(): ApplicationConfig<S> = ApplicationConfig(
        client = public ?: error("Public path has not been set"),
        service = buildService().also { GlobalScope.launch { startCallback?.invoke(it) } },
        daoFactory = buildDaoFactory()
    )

    fun install(builder: (service: S) -> Module) {
        moduleBuilders.add(builder)
    }

    fun buildApplication() = Application(buildApplicationConfig())

    companion object {
        operator fun <S : BitframeService> invoke(): KtorServerConfigurationBuilder<S> = object : KtorServerConfigurationBuilder<S>, ServerConfigurationBuilder<S> by ServerConfigurationBuilder() {
            override var public: File? = null
            override var moduleBuilders: MutableList<(S) -> Module> = mutableListOf()
        }
    }
}