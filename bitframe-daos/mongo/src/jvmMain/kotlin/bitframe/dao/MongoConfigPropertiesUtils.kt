package bitframe.dao

import bitframe.DaoFactoryMongoConfig
import bitframe.DaoMongoConfig
import bitframe.MongoConfigProperties
import com.mongodb.ConnectionString
import java.io.InputStream
import java.util.*
import kotlin.reflect.KClass

fun <D : Any> MongoConfigProperties.daoConfigOf(clazz: KClass<D>): DaoMongoConfig<D> = MongoDaoConfig(
    clazz = clazz,
    host = host,
    username = username,
    password = password,
    database = database
)

fun MongoConfigProperties.connectionString() = ConnectionString("mongodb://$username:$password@$host")

fun MongoConfigProperties(stream: InputStream): MongoConfigProperties {
    val props = Properties().apply { load(stream) }
    return DaoFactoryMongoConfig(
        host = props["host"].toString(),
        username = props["username"].toString(),
        password = props["password"].toString(),
        database = props["database"].toString(),
    )
}