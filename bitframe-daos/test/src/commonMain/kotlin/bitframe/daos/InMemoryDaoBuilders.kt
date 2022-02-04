package bitframe.daos

import bitframe.modal.HasId

inline fun <reified D : HasId> InMemoryDao() = InMemoryDao<D>(InMemoryDaoConfig())