package bitframe.core

inline fun <reified D : Savable> DaoFactory.get(): Dao<D> = get(D::class)

inline fun <reified D : Savable> CompoundDao(vararg daos: Dao<D>): CompoundDao<D> {
    val config = CompoundDaoConfig(
        clazz = D::class,
        daos = daos.associateBy { it.config.clazz },
        scope = daos.firstOrNull()?.config?.scope ?: DaoConfig.DEFAULT_SCOPE
    )
    return CompoundDao(config)
}