package biframe.daos.config

import bitframe.core.IllegalConfiguration
import bitframe.server.MongoDaoConfig
import bitframe.server.MongoDaoFactoryConfig

@Deprecated("In favour of bitframe.dao.toDaoFactoryMongoConfig")
fun DatabaseConfigurationRawMongo.toDaoFactoryMongoConfig() = MongoDaoFactoryConfig(
    host = host ?: throw IllegalConfiguration("database.host for a mongo database must be provided"),
    username = username ?: throw IllegalConfiguration("database.username for a mongo database must be provided"),
    password = password ?: throw IllegalConfiguration("database.password for a mongo database must be provided"),
    database = database ?: MongoDaoConfig.DEFAULT_DATABASE
)