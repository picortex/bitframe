package bitframe.daos

class MongoDaoFactoryConfig(
    override val host: String,
    override val username: String,
    override val password: String,
    override val database: String = MongoDaoConfig.DEFAULT_DATABASE
) : MongoConfigProperties