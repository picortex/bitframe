package bitframe.server

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

interface KtorServerConfigurationBuilder<S> : ServerConfigurationBuilder<S> {
    var public: File?
    fun buildApplicationConfig(): ApplicationConfig<S> = ApplicationConfig(
        client = public ?: error("Public path has not been set"),
        service = buildService().also { GlobalScope.launch { startCallback?.invoke(it) } },
    )

    fun buildApplication() = Application(buildApplicationConfig())

    companion object {
        operator fun <S> invoke(): KtorServerConfigurationBuilder<S> = KtorServerConfigurationBuilderImpl(null)
    }
}