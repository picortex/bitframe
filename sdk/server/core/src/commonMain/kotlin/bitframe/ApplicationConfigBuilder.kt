package bitframe

import bitframe.annotations.BitframeServerDsl
import bitframe.internal.ApplicationConfigBuilderImpl

interface ApplicationConfigBuilder<S> {

    @BitframeServerDsl
    fun database(builder: () -> DaoFactory)

    @BitframeServerDsl
    fun service(builder: (DaoFactory) -> S)

    @BitframeServerDsl
    fun install(builder: (service: S) -> Module)

    fun onStart(block: suspend S.() -> Unit)

    fun buildDaoFactory(): DaoFactory

    fun buildService(): S

    fun buildApplicationConfig(): ApplicationConfig<S>

    fun buildApplication(): Application<S>

    companion object {
        operator fun <S> invoke(): ApplicationConfigBuilder<S> = ApplicationConfigBuilderImpl()
    }
}