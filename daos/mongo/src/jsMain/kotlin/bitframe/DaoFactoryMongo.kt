package bitframe

import bitframe.actor.Savable
import kotlin.reflect.KClass

actual class DaoFactoryMongo actual constructor(config: DaoFactoryMongoConfig) : DaoFactory {
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> {
        TODO("Mongo for js has not yet been implemented")
    }
}