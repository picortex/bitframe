package bitframe

import bitframe.internal.ApplicationConfigBuilderImpl

interface ApplicationConfigBuilder<S> {

    fun database(builder: () -> DaoFactory)

    fun service(builder: (DaoFactory) -> S)

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