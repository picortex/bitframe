package bitframe.daos

import bitframe.actors.modal.HasId
import bitframe.daos.config.DaoConfig

inline fun <reified D : HasId> DaoFactory.get(): Dao<D> = get(D::class)

inline fun <reified D : HasId> CompoundDao(vararg daos: Dao<D>): CompoundDao<D> {
    val config = CompoundDaoConfig(
        clazz = D::class,
        daos = daos.associateBy { it.config.clazz },
        scope = daos.firstOrNull()?.config?.scope ?: DaoConfig.DEFAULT_SCOPE
    )
    return CompoundDao(config)
}