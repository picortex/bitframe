package bitframe

import bitframe.actor.Savable
import bitframe.dao.daoConfigOf
import kotlin.reflect.KClass

actual class DaoFactoryMongo actual constructor(
    val config: DaoFactoryMongoConfig
) : DaoFactory {
    private val daoContainer = mutableMapOf<KClass<out Any>, Dao<Savable>>()
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> = daoContainer.getOrPut(clazz) {
        DaoMongo(config.daoConfigOf(clazz, config.executor))
    } as Dao<D>

    override fun toString() = "MongoDaoFactory(host=${config.host})"
}