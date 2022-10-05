package bitframe.server

import biframe.daos.config.DatabaseConfigurationRawMongo

interface MongoConfigProperties : DatabaseConfigurationRawMongo {
    override val host: String
    override val username: String
    override val password: String
    override val database: String
}