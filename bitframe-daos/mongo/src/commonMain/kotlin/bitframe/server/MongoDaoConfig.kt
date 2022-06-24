package bitframe.server

import bitframe.DaoConfig
import kotlin.jvm.JvmField

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