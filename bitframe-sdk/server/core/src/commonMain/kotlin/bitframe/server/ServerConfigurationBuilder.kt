package bitframe.server

import bitframe.core.DaoFactory

interface ServerConfigurationBuilder<S : BitframeService> {
    var daoFactoryBuilder: (() -> DaoFactory)?

    var serviceConfigBuilder: ((DaoFactory) -> S)?

    var startCallback: (suspend S.() -> Unit)?

    fun database(builder: () -> DaoFactory) {
        daoFactoryBuilder = builder
    }

    fun service(builder: (DaoFactory) -> S) {
        serviceConfigBuilder = builder
    }

    fun onStart(block: suspend S.() -> Unit) {
        startCallback = block
    }

    fun buildDaoFactory(): DaoFactory

    fun buildService(): S

    companion object {
        operator fun <S : BitframeService> invoke() = object : ServerConfigurationBuilder<S> {
            override var daoFactoryBuilder: (() -> DaoFactory)? = null

            override var serviceConfigBuilder: ((DaoFactory) -> S)? = null

            override var startCallback: (suspend (S) -> Unit)? = null

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
    }
}