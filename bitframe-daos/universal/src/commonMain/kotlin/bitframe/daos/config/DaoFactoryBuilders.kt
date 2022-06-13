@file:JvmName("DaoFactoryBuilder")

package bitframe.daos.config

import biframe.daos.config.DaoFactoryMongo
import bitframe.core.*
import kotlin.jvm.JvmName

@JvmName("from")
fun DaoFactory(configuration: DatabaseConfiguration): DaoFactory {
    val instance = configuration.instance?.lowercase() ?: throw IllegalConfiguration("database instance must not be null")
    return when (instance) {
        "mock" -> DaoFactoryMock(configuration)
        "mongo" -> DaoFactoryMongo(configuration)
        else -> throw IllegalConfiguration("Unsupported database instance $instance")
    }
}