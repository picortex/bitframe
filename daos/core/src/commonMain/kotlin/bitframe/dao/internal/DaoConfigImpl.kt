package bitframe.dao.internal

import bitframe.DaoConfig
import koncurrent.Executor
import kotlin.reflect.KClass

class DaoConfigImpl<D : Any>(
    override val clazz: KClass<D>,
    override val executor: Executor
) : DaoConfig<D>