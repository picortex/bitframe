package bitframe

import koncurrent.Executor
import kotlinx.serialization.StringFormat
import okio.FileSystem
import okio.Path
import kotlin.reflect.KClass

class DaoFileConfigImpl<D : Any>(
    override val clazz: KClass<D>,
    override val executor: Executor,
    override val fs: FileSystem,
    override val directory: Path,
    override val codec: StringFormat
) : DaoFileConfig<D>