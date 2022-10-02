package bitframe.server

import bitframe.DaoFactory
import bitframe.Module

interface ServerConfigurationBuilder<S> {
    var daoFactoryBuilder: (() -> DaoFactory)?

    var serviceConfigBuilder: ((DaoFactory) -> S)?

    var startCallback: (suspend S.() -> Unit)?

    val moduleBuilders: MutableList<(S) -> Module>

    fun database(builder: () -> DaoFactory) {
        daoFactoryBuilder = builder
    }

    fun service(builder: (DaoFactory) -> S) {
        serviceConfigBuilder = builder
    }

    fun install(builder: (service: S) -> Module) {
        moduleBuilders.add(builder)
    }

    fun onStart(block: suspend S.() -> Unit) {
        startCallback = block
    }

    fun buildDaoFactory(): DaoFactory

    fun buildService(): S

    companion object {
        operator fun <S> invoke(): ServerConfigurationBuilder<S> = ServerConfigurationBuilderImpl()
    }
}