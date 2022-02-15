package bitframe.server

import bitframe.core.Dao
import bitframe.core.DaoFactory
import bitframe.core.Savable
import kotlin.reflect.KClass

class MongoDaoFactory(
    val config: MongoDaoFactoryConfig
) : DaoFactory {
    private val daoContainer = mutableMapOf<KClass<*>, Dao<*>>()
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> = daoContainer.getOrPut(clazz) {
        MongoDao(config.daoConfigOf(clazz))
    } as Dao<D>
}