package bitframe.daos

import bitframe.daos.config.DaoConfig
import com.mongodb.ConnectionString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlin.jvm.JvmField
import kotlin.reflect.KClass

interface MongoDaoConfig<D : Any> : DaoConfig<D>, MongoConfigProperties {
    val collection: String
    val prefix: String

    companion object {
        @JvmField
        val DEFAULT_DATABASE = "app"

        @JvmField
        val DEFAULT_SCOPE = DaoConfig.DEFAULT_SCOPE

        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            host: String,
            username: String,
            password: String,
            database: String = DEFAULT_DATABASE,
            collection: String? = null,
            prefix: String? = null,
            scope: CoroutineScope = DEFAULT_SCOPE
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

        inline operator fun <reified D : Any> invoke(
            host: String,
            username: String,
            password: String,
            database: String = DEFAULT_DATABASE,
            collection: String? = null,
            prefix: String? = null,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(D::class, host, username, password, database, collection, prefix, scope)

        @JvmStatic
        fun <T : Any> defaultCollectionNameOf(clazz: KClass<T>): String? {
            val qualifiedName = clazz.qualifiedName ?: return null
            return "${qualifiedName}s".split(".").takeLast(2).joinToString(separator = ".")
        }
    }
}