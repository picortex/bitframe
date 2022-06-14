@file:Suppress("NOTHING_TO_INLINE", "FunctionName")

package biframe.daos.config

import bitframe.server.MongoDaoFactory

inline fun DaoFactoryMongo(configuration: DatabaseConfigurationRawMongo) = MongoDaoFactory(configuration.toDaoFactoryMongoConfig())