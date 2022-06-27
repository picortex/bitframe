package bitframe

import kotlin.jvm.JvmField

interface DaoMongoConfig<D : Any> : DaoConfig<D>, MongoConfigProperties {
    val collection: String
    val prefix: String

    companion object {
        @JvmField
        val DEFAULT_DATABASE = "app"
    }
}