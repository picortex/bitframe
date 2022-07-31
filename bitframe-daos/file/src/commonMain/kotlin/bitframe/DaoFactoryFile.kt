package bitframe

import bitframe.actor.Savable
import okio.Path.Companion.toPath
import kotlin.reflect.KClass

class DaoFactoryFile(val config: DaoFactoryFileConfig) : DaoFactory {
    override fun <D : Savable> get(clazz: KClass<D>): Dao<D> {
        val dir = config.db / "${clazz.simpleName}".toPath()
        return DaoFile(
            config = DaoFileConfig(clazz, config.fs, dir, config.executor, config.codec)
        )
    }
}