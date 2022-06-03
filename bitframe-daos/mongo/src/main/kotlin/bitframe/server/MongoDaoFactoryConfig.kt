package bitframe.server

import java.io.InputStream

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

    companion object {
        fun fromProperties(stream: InputStream) = MongoDaoFactoryConfig(
            MongoConfigProperties.from(stream)
        )
    }
}