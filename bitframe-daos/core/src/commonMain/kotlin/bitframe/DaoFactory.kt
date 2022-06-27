package bitframe

import bitframe.actor.Savable
import kotlin.reflect.KClass

interface DaoFactory {
    fun <D : Savable> get(clazz: KClass<D>): Dao<D>
}