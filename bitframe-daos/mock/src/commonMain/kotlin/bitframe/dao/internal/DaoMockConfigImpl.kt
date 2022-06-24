package bitframe.dao.internal

import bitframe.DaoMockConfig
import koncurrent.Executor
import kotlin.reflect.KClass

internal class DaoMockConfigImpl<D : Any>(
    override val clazz: KClass<D>,
    override val executor: Executor,
    override val items: MutableMap<String, D>,
    override val simulationTime: Long,
    override val namespace: String,
    override val prefix: String,
) : DaoMockConfig<D>