package bitframe

interface MongoConfigProperties : DaoFactoryMongoConfigRaw {
    override val host: String
    override val username: String
    override val password: String
    override val database: String
}