package bitframe

import koncurrent.Executor
import koncurrent.Executors
import kotlin.jvm.JvmOverloads

class DaoFactoryMongoConfig @JvmOverloads constructor(
    override val host: String,
    override val username: String,
    override val password: String,
    override val database: String = DaoMongoConfig.DEFAULT_DATABASE,
    val executor: Executor = Executors.default(),
) : MongoConfigProperties