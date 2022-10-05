package bitframe.core

@Deprecated("in favour of its quivalent in bitframe.dao")
inline fun <reified D : Savable> DaoFactory.get(): Dao<D> = get(D::class)

@Deprecated("in favour of its quivalent in bitframe.dao")

inline fun <reified D : Savable> CompoundDao(vararg daos: Dao<D>): CompoundDao<D> {
    val config = CompoundDaoConfig(
        clazz = D::class,
        daos = daos.associateBy { it.config.clazz },
        scope = daos.firstOrNull()?.config?.scope ?: DaoConfig.DEFAULT_SCOPE
    )
    return CompoundDao(config)
}