package bitframe.server

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface KtorServerConfigurationBuilder<S> : ServerConfigurationBuilder<S> {
    fun buildApplicationConfig(): ApplicationConfig<S> = ApplicationConfig(
        service = buildService().also { GlobalScope.launch { startCallback?.invoke(it) } },
    )

    fun buildApplication() = Application(buildApplicationConfig())

    companion object {
        operator fun <S> invoke(): KtorServerConfigurationBuilder<S> = KtorServerConfigurationBuilderImpl()
    }
}