package bitframe.server

import bitframe.core.Dao
import bitframe.core.DaoFactory
import bitframe.core.Savable
import kotlin.reflect.KClass

actual class MongoDaoFactory actual constructor(
    val config: MongoDaoFactoryConfig
) : DaoFactory {
    private val daoContainer = mutableMapOf<KClass<out Any>, Dao<Savable>>()
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> = daoContainer.getOrPut(clazz) {
        MongoDao(config.daoConfigOf(clazz))
    } as Dao<D>

    override fun toString() = "MongoDaoFactory(host=${config.host})"
}