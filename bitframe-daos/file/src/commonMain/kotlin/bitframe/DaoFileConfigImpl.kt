package bitframe

import koncurrent.Executor
import okio.FileSystem
import kotlin.reflect.KClass

class DaoFileConfigImpl<D : Any>(
    override val clazz: KClass<D>,
    override val executor: Executor,
    override val fs: FileSystem
) : DaoFileConfig<D>