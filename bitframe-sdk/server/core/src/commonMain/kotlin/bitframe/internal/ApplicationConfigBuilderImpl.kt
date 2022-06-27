package bitframe.internal

import bitframe.Application
import bitframe.ApplicationConfig
import bitframe.ApplicationConfigBuilder
import bitframe.Module
import bitframe.DaoFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class ApplicationConfigBuilderImpl<S> : ApplicationConfigBuilder<S> {
    var daoFactoryBuilder: (() -> DaoFactory)? = null

    var serviceConfigBuilder: ((DaoFactory) -> S)? = null

    var startCallback: (suspend (S) -> Unit)? = null

    val moduleBuilders: MutableList<(S) -> Module> = mutableListOf()

    private var daoFactory: DaoFactory? = null

    private var service: S? = null

    override fun database(builder: () -> DaoFactory) {
        daoFactoryBuilder = builder
    }

    override fun service(builder: (DaoFactory) -> S) {
        serviceConfigBuilder = builder
    }

    override fun install(builder: (service: S) -> Module) {
        moduleBuilders.add(builder)
    }

    override fun onStart(block: suspend S.() -> Unit) {
        startCallback = block
    }

    override fun buildDaoFactory(): DaoFactory {
        val factory = daoFactory
        if (factory != null) return factory
        daoFactory = daoFactoryBuilder?.invoke() ?: error("Database has not been configured yet")
        return buildDaoFactory()
    }

    override fun buildService(): S {
        val s = service
        if (s != null) return s
        service = serviceConfigBuilder?.invoke(buildDaoFactory()) ?: error("Service has not been configured yet")
        return buildService()
    }

    override fun buildApplicationConfig(): ApplicationConfig<S> {
        val service = buildService()
        GlobalScope.launch {
            startCallback?.invoke(service)
        }
        return ApplicationConfig(
            service = service,
            modules = moduleBuilders.map { builder -> builder(service) }
        )
    }

    override fun buildApplication() = Application(buildApplicationConfig())
}