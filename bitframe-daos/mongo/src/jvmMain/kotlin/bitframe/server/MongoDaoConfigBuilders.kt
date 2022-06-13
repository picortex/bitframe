package bitframe.server

import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

fun <D : Any> MongoDaoConfig(
    clazz: KClass<D>,
    host: String,
    username: String,
    password: String,
    database: String = MongoDaoConfig.DEFAULT_DATABASE,
    collection: String? = null,
    prefix: String? = null,
    scope: CoroutineScope = MongoDaoConfig.DEFAULT_SCOPE
) = object : MongoDaoConfig<D> {
    override val scope: CoroutineScope = scope
    override val clazz: KClass<D> = clazz
    override val host: String = host
    override val username: String = username
    override val password: String = password
    override val database: String = database
    override val collection: String = collection ?: defaultCollectionNameOf(clazz) ?: error("Can't get collection name")
    override val prefix: String = prefix ?: clazz.simpleName?.lowercase() ?: error("Can't get id prefix")
}

inline fun <reified D : Any> MongoDaoConfig(
    host: String,
    username: String,
    password: String,
    database: String = MongoDaoConfig.DEFAULT_DATABASE,
    collection: String? = null,
    prefix: String? = null,
    scope: CoroutineScope = MongoDaoConfig.DEFAULT_SCOPE
) = MongoDaoConfig(D::class, host, username, password, database, collection, prefix, scope)

internal fun <T : Any> defaultCollectionNameOf(clazz: KClass<T>): String? {
    val qualifiedName = clazz.qualifiedName ?: return null
    return "${qualifiedName}s".split(".").takeLast(2).joinToString(separator = ".")
}