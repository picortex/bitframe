package bitframe.core

import kotlin.reflect.KClass

@Deprecated("In favour of bitframe.MockDaoFactory")
class MockDaoFactory(
    private val config: MockDaoFactoryConfig = MockDaoFactoryConfig()
) : DaoFactory {
    private val container = mutableMapOf<KClass<out Any>, Dao<Savable>>()
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> = container.getOrPut(clazz) {
        val cfg = MockDaoConfig(
            clazz = clazz,
            simulationTime = config.simulationTime,
            scope = config.scope
        )
        MockDao(cfg)
    } as Dao<D>

    override fun toString(): String = "MockDaoFactory(simulationTime=${config.simulationTime}ms)"
}