package bitframe.daos

import bitframe.modal.HasId

inline fun <reified D : HasId> DaoFactory.get(): Dao<D> = get(D::class)