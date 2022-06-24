package biframe.daos.config

@Deprecated("In favour of bitframe.DaoFactoryMongoConfigRaw")
interface DatabaseConfigurationRawMongo {
    val host: String?
    val username: String?
    val password: String?
    val database: String?
}