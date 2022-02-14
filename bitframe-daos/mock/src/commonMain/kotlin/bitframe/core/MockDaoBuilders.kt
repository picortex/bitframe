package bitframe.core

inline fun <reified D : Savable> MockDao(
    config: MockDaoConfig<D>? = null
): MockDao<D> = MockDao(config ?: MockDaoConfig())