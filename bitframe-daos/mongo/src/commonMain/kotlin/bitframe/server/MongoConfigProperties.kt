package bitframe.server

import biframe.daos.config.DatabaseConfigurationMongo

interface MongoConfigProperties : DatabaseConfigurationMongo {
    override val host: String
    override val username: String
    override val password: String
    override val database: String
}