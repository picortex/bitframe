package bitframe.service.daod

import bitframe.actors.modal.Savable
import bitframe.service.GenericService
import bitframe.service.daod.config.GenericDaodServiceConfig

open class GenericDaodService<T : Savable>(
    override val config: GenericDaodServiceConfig<T>
) : GenericService<T> {

    private val dao by lazy { config.daoFactory.get(config.clazz) }

    override fun create(input: T) = dao.create(input)

    override fun update(obj: T) = dao.update(obj)

    override fun load(uid: String) = dao.load(uid)

    override fun loadOrNull(uid: String) = dao.loadOrNull(uid)

    override fun delete(uid: String) = dao.delete(uid)

    override fun all() = dao.all()
}