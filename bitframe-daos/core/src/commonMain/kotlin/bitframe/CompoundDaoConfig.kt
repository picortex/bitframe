package bitframe

import kotlin.reflect.KClass

class CompoundDaoConfig<T : Any>(
    val clazz: KClass<T>,
    val daos: Map<KClass<T>, Dao<T>>,
)