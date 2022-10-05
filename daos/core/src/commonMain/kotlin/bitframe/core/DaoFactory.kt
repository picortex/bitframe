package bitframe.core

import kotlin.reflect.KClass

@Deprecated("in favour of its quivalent in bitframe.dao")
interface DaoFactory {
    fun <D : Savable> get(clazz: KClass<D>): Dao<D>
}