package bitframe.service.server

import bitframe.actors.modal.HasId
import kotlinx.collections.interoperable.List
import later.Later
import bitframe.service.GenericService as CoreGenericService

open class GenericService<T : HasId>(override val config: GenericServiceConfig<T>) : CoreGenericService<T>(config) {

    private val dao by lazy { config.daoFactory.get(config.clazz) }

    override fun create(input: T) = dao.create(input)

    override fun update(obj: T) = dao.update(obj)

    override fun load(uid: String) = dao.load(uid)

    override fun loadOrNull(uid: String) = dao.loadOrNull(uid)

    override fun delete(uid: String) = dao.delete(uid)

    override fun all() = dao.all()
}