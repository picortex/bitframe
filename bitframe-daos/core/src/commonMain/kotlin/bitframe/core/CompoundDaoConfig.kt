package bitframe.core

import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

class CompoundDaoConfig<T : Any>(
    override val clazz: KClass<T>,
    val daos: Map<KClass<T>, Dao<T>>,
    override val scope: CoroutineScope = DaoConfig.DEFAULT_SCOPE
) : DaoConfig<T>