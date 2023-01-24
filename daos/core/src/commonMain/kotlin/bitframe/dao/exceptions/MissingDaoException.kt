package bitframe.dao.exceptions

import bitframe.CompoundDao
import bitframe.actor.Savable
import kotlin.reflect.KClass

class MissingDaoException(
    val clazz: KClass<out Any>,
    val compound: CompoundDao<out Savable>
) : IllegalStateException(
    "Dao for class ${clazz.simpleName} is missing in ${compound::class.simpleName}"
)