package bitframe.server

import bitframe.Dao
import bitframe.DaoFactory
import bitframe.core.Savable
import kotlin.reflect.KClass

actual class MongoDaoFactory actual constructor(config: MongoDaoFactoryConfig) : DaoFactory {
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> {
        TODO("Mongo for js has not yet been implemented")
    }
}