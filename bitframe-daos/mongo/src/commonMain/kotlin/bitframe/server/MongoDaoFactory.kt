package bitframe.server

import bitframe.core.Dao
import bitframe.core.DaoFactory
import bitframe.core.Savable
import kotlin.reflect.KClass

expect class MongoDaoFactory(config: MongoDaoFactoryConfig) : DaoFactory