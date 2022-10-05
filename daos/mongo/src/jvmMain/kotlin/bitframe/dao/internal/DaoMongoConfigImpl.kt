package bitframe.dao.internal

import bitframe.DaoMongoConfig
import koncurrent.Executor
import kotlin.reflect.KClass

class DaoMongoConfigImpl<D : Any>(
    override val clazz: KClass<D>,
    override val executor: Executor,
    override val host: String,
    override val username: String,
    override val password: String,
    override val database: String,
    override val collection: String,
    override val prefix: String,
) : DaoMongoConfig<D>