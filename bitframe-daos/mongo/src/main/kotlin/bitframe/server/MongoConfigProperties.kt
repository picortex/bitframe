package bitframe.server

import com.mongodb.ConnectionString
import kotlin.reflect.KClass

interface MongoConfigProperties {
    val host: String
    val username: String
    val password: String
    val database: String

    fun <D : Any> daoConfigOf(clazz: KClass<D>): MongoDaoConfig<D> = MongoDaoConfig(
        clazz = clazz,
        host = host,
        username = username,
        password = password,
        database = database
    )

    fun connectionString() = ConnectionString("mongodb://$username:$password@$host")
}