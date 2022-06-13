package bitframe.daos.config

import biframe.daos.config.DatabaseConfigurationMongo
import kotlinx.serialization.Serializable

@Serializable
class DatabaseConfiguration(
    val instance: String?,
    override val host: String?,
    override val username: String?,
    override val password: String?,
    override val database: String?,
    override val simulationTime: Long?
) : DatabaseConfigurationMongo, DatabaseConfigurationMock