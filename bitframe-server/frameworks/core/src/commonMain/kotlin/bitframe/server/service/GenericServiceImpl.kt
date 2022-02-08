package bitframe.server.service

import bitframe.daos.conditions.Condition
import bitframe.actors.modal.HasId
import kotlinx.collections.interoperable.List
import later.Later

class GenericServiceImpl<T : HasId>(val config: GenericServiceConfig<T>) : GenericService<T> {
    private val dao by lazy { config.daoFactory.get(config.clazz) }

    override fun create(input: T) = dao.create(input)

    override fun update(obj: T) = dao.update(obj)

    override fun load(uid: String) = dao.load(uid)

    override fun loadOrNull(uid: String) = dao.loadOrNull(uid)

    override fun delete(uid: String) = dao.delete(uid)

    override fun all(condition: Condition<String, Any>?) = dao.all(condition)
}