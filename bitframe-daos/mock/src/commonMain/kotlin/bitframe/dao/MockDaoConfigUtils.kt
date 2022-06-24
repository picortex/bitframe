package bitframe.dao

import bitframe.DaoMockConfig
import bitframe.dao.internal.DaoMockConfigImpl

internal inline fun <D : Any, reified R : Any> DaoMockConfig<D>.map(): DaoMockConfig<R> = DaoMockConfigImpl<R>(
    clazz = R::class,
    executor = executor,
    mutableMapOf(),
    simulationTime,
    namespace,
    prefix
)