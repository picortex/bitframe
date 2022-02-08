package bitframe.daos

import bitframe.actors.modal.HasId
import kotlin.reflect.KClass

class MongoDaoFactory(
    val config: MongoDaoFactoryConfig
) : DaoFactory {
    private val daoContainer = mutableMapOf<KClass<*>, Dao<*>>()
    override fun <D : HasId> get(clazz: KClass<D>): Dao<D> = daoContainer.getOrPut(clazz) {
        MongoDao(config.daoConfigOf(clazz))
    } as Dao<D>
}