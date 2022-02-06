package bitframe.server.service

import bitframe.daos.conditions.Condition
import bitframe.modal.HasId
import kotlinx.collections.interoperable.List
import later.Later

class GenericServiceImpl<T : HasId>(val config: GenericServiceConfig<T>) : GenericService<T> {
    private val dao by lazy { config.daoFactory.get(config.clazz) }

    override fun create(input: T): Later<T> = dao.create(input)

    override fun update(obj: T): Later<T> = dao.update(obj)

    override fun load(uid: String): Later<T> = dao.load(uid)

    override fun loadOrNull(uid: String): Later<T?> = dao.loadOrNull(uid)

    override fun delete(uid: String): Later<T> = dao.delete(uid)

    override fun all(condition: Condition<String, Any>?): Later<List<T>> = dao.all(condition)
}