package bitframe.dao

import bitframe.*
import bitframe.actor.Savable
import kotlin.reflect.KClass

inline fun <reified D : Savable> DaoFactory.get(): Dao<D> = get(D::class)

inline fun <reified D : Savable> CompoundDao(vararg daos: Pair<KClass<D>, Dao<D>>): CompoundDao<D> {
    return CompoundDao(CompoundDaoConfig(clazz = D::class, daos = daos.toMap()))
}