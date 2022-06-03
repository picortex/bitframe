package bitframe.core

internal inline fun <D : Any, reified R : Any> MockDaoConfig<D>.map(): MockDaoConfig<R> = MockDaoConfigImpl<R>(
    clazz = R::class,
    mutableMapOf(),
    simulationTime,
    namespace,
    prefix,
    lock,
    scope
)