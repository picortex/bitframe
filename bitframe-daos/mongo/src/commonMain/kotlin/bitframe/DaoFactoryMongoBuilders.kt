@file:Suppress("NOTHING_TO_INLINE", "FunctionName")

package bitframe

import bitframe.dao.toDaoFactoryMongoConfig
import bitframe.server.MongoDaoFactory

inline fun DaoFactoryMongo(configuration: DaoFactoryMongoConfigRaw) = MongoDaoFactory(configuration.toDaoFactoryMongoConfig())