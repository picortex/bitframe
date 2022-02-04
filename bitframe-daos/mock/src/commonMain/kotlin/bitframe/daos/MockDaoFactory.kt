package bitframe.daos

import bitframe.modal.HasId
import kotlin.reflect.KClass

class MockDaoFactory : DaoFactory {
    private val daoContainer = mutableMapOf<KClass<*>, Dao<*>>()
    override fun <D : HasId> get(clazz: KClass<D>): Dao<D> = daoContainer.getOrPut(clazz) { MockDao(clazz) } as Dao<D>
}