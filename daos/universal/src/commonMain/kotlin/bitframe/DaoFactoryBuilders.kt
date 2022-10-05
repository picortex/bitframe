@file:JvmName("DaoFactoryBuilder")

package bitframe

import bitframe.exceptions.IllegalConfiguration
import kotlin.jvm.JvmName
import okio.FileSystem

@JvmName("from")
fun DaoFactory(configuration: DaoFactoryConfigRaw): DaoFactory {
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
): DaoFactory = DaoFactory(DaoFactoryConfigRaw(fs, appDir, appConf))