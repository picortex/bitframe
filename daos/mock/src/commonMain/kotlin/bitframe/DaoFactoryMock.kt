package bitframe

import bitframe.actor.Savable
import kotlin.reflect.KClass

class DaoFactoryMock(
    private val config: DaoFactoryMockConfig = DaoFactoryMockConfig()
) : DaoFactory {
    private val container = mutableMapOf<KClass<*>, Dao<*>>()
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> = container.getOrPut(clazz) {
        val cfg = DaoMockConfig(
            clazz = clazz,
            simulationTime = config.simulationTime,
        )
        DaoMock(cfg)
    } as Dao<D>

    override fun toString(): String = "DaoMockFactory(simulationTime=${config.simulationTime}ms)"
}