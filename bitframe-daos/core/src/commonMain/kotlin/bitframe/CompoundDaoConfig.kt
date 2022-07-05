package bitframe

import kotlin.reflect.KClass

class CompoundDaoConfig<out T : Any>(
    val clazz: KClass<@UnsafeVariance T>,
    val daos: Map<KClass<@UnsafeVariance T>, Dao<T>>,
)