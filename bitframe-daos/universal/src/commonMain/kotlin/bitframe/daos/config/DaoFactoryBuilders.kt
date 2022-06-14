@file:JvmName("DaoFactoryBuilder")

package bitframe.daos.config

import biframe.daos.config.DaoFactoryMongo
import bitframe.core.*
import kotlin.jvm.JvmName
import okio.FileSystem

@JvmName("from")
fun DaoFactory(configuration: DatabaseConfigurationRaw): DaoFactory {
    val instance = configuration.instance?.lowercase() ?: throw IllegalConfiguration("database instance must not be null")
    return when (instance) {
        "mock" -> DaoFactoryMock(configuration)
        "mongo" -> DaoFactoryMongo(configuration)
        else -> throw IllegalConfiguration("Unsupported database instance $instance")
    }
}

@JvmName("from")
fun DaoFactory(
    fs: FileSystem,
    appDir: String?,
    appConf: String?,
): DaoFactory = DaoFactory(DatabaseConfigurationRaw(fs, appDir, appConf))