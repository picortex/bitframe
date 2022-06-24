@file:Suppress("NOTHING_TO_INLINE", "FunctionName")

package biframe.daos.config

import bitframe.server.MongoDaoFactory

@Deprecated("In favour of bitframe.DaoFactoryMongo")
inline fun DaoFactoryMongo(configuration: DatabaseConfigurationRawMongo) = MongoDaoFactory(configuration.toDaoFactoryMongoConfig())