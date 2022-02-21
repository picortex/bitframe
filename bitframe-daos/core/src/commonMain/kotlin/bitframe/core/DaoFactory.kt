package bitframe.core

import kotlin.reflect.KClass

interface DaoFactory {
    fun <D : Savable> get(clazz: KClass<D>): Dao<D>
}