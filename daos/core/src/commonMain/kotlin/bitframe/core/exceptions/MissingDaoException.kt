package bitframe.core.exceptions

import bitframe.core.CompoundDao
import kotlin.reflect.KClass

class MissingDaoException(
    val clazz: KClass<out Any>,
    val compound: CompoundDao<out Any>
) : IllegalStateException(
    "Dao for class ${clazz.simpleName} is missing in ${compound::class.simpleName}"
)