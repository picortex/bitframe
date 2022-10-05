package bitframe

import bitframe.dao.internal.DaoMongoConfigImpl
import koncurrent.Executor
import kotlin.reflect.KClass

fun <D : Any> DaoMongoConfig(
    clazz: KClass<D>,
    executor: Executor,
    host: String,
    username: String,
    password: String,
    database: String = DaoMongoConfig.DEFAULT_DATABASE,
    collection: String? = null,
    prefix: String? = null,
): DaoMongoConfig<D> = DaoMongoConfigImpl(
    clazz = clazz,
    executor = executor,
    host = host,
    username = username,
    password = password,
    database = database,
    collection = collection ?: defaultCollectionNameOf(clazz) ?: error("Can't get collection name"),
    prefix = prefix ?: clazz.simpleName?.lowercase() ?: error("Can't get id prefix"),
)

inline fun <reified D : Any> DaoMongoConfig(
    executor: Executor,
    host: String,
    username: String,
    password: String,
    database: String = DaoMongoConfig.DEFAULT_DATABASE,
    collection: String? = null,
    prefix: String? = null,
): DaoMongoConfig<D> = DaoMongoConfig(D::class, executor, host, username, password, database, collection, prefix)

internal fun <T : Any> defaultCollectionNameOf(clazz: KClass<T>): String? {
    val qualifiedName = clazz.qualifiedName ?: return null
    return "${qualifiedName}s".split(".").takeLast(2).joinToString(separator = ".")
}