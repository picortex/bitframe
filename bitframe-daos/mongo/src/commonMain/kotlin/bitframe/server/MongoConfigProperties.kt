package bitframe.server

interface MongoConfigProperties {
    val host: String
    val username: String
    val password: String
    val database: String
}