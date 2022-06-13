package bitframe.server

import kotlin.jvm.JvmOverloads

class MongoDaoFactoryConfig @JvmOverloads constructor(
    override val host: String,
    override val username: String,
    override val password: String,
    override val database: String = MongoDaoConfig.DEFAULT_DATABASE
) : MongoConfigProperties {
    constructor(properties: MongoConfigProperties) : this(
        host = properties.host,
        username = properties.username,
        password = properties.password,
        database = properties.database,
    )
}