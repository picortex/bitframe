package bitframe.server

import bitframe.DaoFactory

internal class ServerConfigurationBuilderImpl<S> : ServerConfigurationBuilder<S> {
    override var daoFactoryBuilder: (() -> DaoFactory)? = null

    override var serviceConfigBuilder: ((DaoFactory) -> S)? = null

    override var startCallback: (suspend (S) -> Unit)? = null

    override val moduleBuilders: MutableList<(S) -> Module> = mutableListOf()

    private var daoFactory: DaoFactory? = null

    private var service: S? = null

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
}