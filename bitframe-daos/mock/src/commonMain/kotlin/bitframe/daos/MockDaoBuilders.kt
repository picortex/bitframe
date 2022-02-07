package bitframe.daos

import bitframe.actors.modal.HasId

inline fun <reified D : HasId> MockDao(
    config: MockDaoConfig<D>? = null
): MockDao<D> = MockDao(config ?: MockDaoConfig())