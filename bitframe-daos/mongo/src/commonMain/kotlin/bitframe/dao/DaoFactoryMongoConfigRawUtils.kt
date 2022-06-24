package bitframe.dao

import bitframe.DaoFactoryMongoConfig
import bitframe.DaoFactoryMongoConfigRaw
import bitframe.DaoMongoConfig
import bitframe.exceptions.IllegalConfiguration

fun DaoFactoryMongoConfigRaw.toDaoFactoryMongoConfig() = DaoFactoryMongoConfig(
    host = host ?: throw IllegalConfiguration("database.host for a mongo database must be provided"),
    username = username ?: throw IllegalConfiguration("database.username for a mongo database must be provided"),
    password = password ?: throw IllegalConfiguration("database.password for a mongo database must be provided"),
    database = database ?: DaoMongoConfig.DEFAULT_DATABASE
)