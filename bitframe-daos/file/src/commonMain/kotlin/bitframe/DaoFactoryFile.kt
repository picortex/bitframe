package bitframe

import bitframe.actor.Savable
import kotlin.reflect.KClass

class DaoFactoryFile(val config: DaoFactoryFileConfig) : DaoFactory {
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> = DaoFile(
        config = DaoFileConfig(clazz,config.fs,config.executor)
    )
}