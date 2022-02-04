package bitframe.daos

import bitframe.modal.HasId

inline fun <reified D : HasId> MockDao() = MockDao<D>(MockDaoConfig())