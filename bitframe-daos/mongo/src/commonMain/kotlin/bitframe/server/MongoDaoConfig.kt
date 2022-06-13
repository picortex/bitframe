package bitframe.server

import bitframe.core.DaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import kotlin.reflect.KClass

interface MongoDaoConfig<D : Any> : DaoConfig<D>, MongoConfigProperties {
    val collection: String
    val prefix: String

    companion object {
        @JvmField
        val DEFAULT_DATABASE = "app"

        @JvmField
        val DEFAULT_SCOPE = DaoConfig.DEFAULT_SCOPE
    }
}