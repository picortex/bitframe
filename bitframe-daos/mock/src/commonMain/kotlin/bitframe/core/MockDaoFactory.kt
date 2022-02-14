package bitframe.core

import kotlin.reflect.KClass

class MockDaoFactory : DaoFactory {
    private val container = mutableMapOf<KClass<*>, Dao<*>>()
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> = container.getOrPut(clazz) { bitframe.core.MockDao(clazz) } as Dao<D>
}