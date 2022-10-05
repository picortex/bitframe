@file:Suppress("NOTHING_TO_INLINE", "FunctionName")

package bitframe

import bitframe.dao.toDaoFactoryMongoConfig

inline fun DaoFactoryMongo(configuration: DaoFactoryMongoConfigRaw): DaoFactoryMongo = DaoFactoryMongo(config = configuration.toDaoFactoryMongoConfig())