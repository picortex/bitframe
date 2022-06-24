package bitframe.dao

import bitframe.DaoFactoryMongoConfigRaw
import bitframe.exceptions.IllegalConfiguration
import bitframe.server.MongoDaoConfig
import bitframe.server.MongoDaoFactoryConfig

fun DaoFactoryMongoConfigRaw.toDaoFactoryMongoConfig() = MongoDaoFactoryConfig(
    host = host ?: throw IllegalConfiguration("database.host for a mongo database must be provided"),
    username = username ?: throw IllegalConfiguration("database.username for a mongo database must be provided"),
    password = password ?: throw IllegalConfiguration("database.password for a mongo database must be provided"),
    database = database ?: MongoDaoConfig.DEFAULT_DATABASE
)