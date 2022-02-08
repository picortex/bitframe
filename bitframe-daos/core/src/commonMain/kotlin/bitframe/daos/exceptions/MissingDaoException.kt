package bitframe.daos.exceptions

import bitframe.daos.CompoundDao
import kotlin.reflect.KClass

class MissingDaoException(
    val clazz: KClass<*>,
    val compound: CompoundDao<*>
) : IllegalStateException(
    "Dao for class ${clazz.qualifiedName} is missing in compound dao ${compound::class.qualifiedName}"
)