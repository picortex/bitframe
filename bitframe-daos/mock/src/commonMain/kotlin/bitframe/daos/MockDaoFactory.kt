package bitframe.daos

import bitframe.actors.modal.HasId
import kotlin.reflect.KClass

class MockDaoFactory : DaoFactory {
    private val container = mutableMapOf<KClass<*>, Dao<*>>()
    override fun <D : HasId> get(clazz: KClass<D>): Dao<D> = container.getOrPut(clazz) { MockDao(clazz) } as Dao<D>
}