package bitframe.core

@Deprecated("in favour of bitframe.MockDao")
inline fun <reified D : Savable> MockDao(
    config: MockDaoConfig<D>? = null
): MockDao<D> = MockDao(config ?: MockDaoConfig())