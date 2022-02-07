package bitframe.server

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

interface KtorServerConfigurationBuilder<S : BitframeService> : ServerConfigurationBuilder<S> {
    var public: File?

    fun buildApplicationConfig(): ApplicationConfig<S> = ApplicationConfig(
        client = public ?: error("Public path has not been set"),
        service = buildService().also { GlobalScope.launch { startCallback?.invoke(it) } },
        daoFactory = buildDaoFactory()
    )

    fun buildApplication() = Application(buildApplicationConfig())

    companion object {
        operator fun <S : BitframeService> invoke(): KtorServerConfigurationBuilder<S> = object : KtorServerConfigurationBuilder<S>, ServerConfigurationBuilder<S> by ServerConfigurationBuilder() {
            override var public: File? = null
        }
    }
}