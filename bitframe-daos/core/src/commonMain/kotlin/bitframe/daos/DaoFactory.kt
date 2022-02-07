package bitframe.daos

import bitframe.actors.modal.HasId
import kotlin.reflect.KClass

interface DaoFactory {
    fun <D : HasId> get(clazz: KClass<D>): Dao<D>
}