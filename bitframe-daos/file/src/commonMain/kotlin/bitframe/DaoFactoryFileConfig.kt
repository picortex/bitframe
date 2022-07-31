package bitframe

import koncurrent.Executor
import kotlinx.serialization.StringFormat
import okio.FileSystem
import okio.Path
import kotlin.jvm.JvmOverloads

class DaoFactoryFileConfig @JvmOverloads constructor(
    val fs: FileSystem,
    val db: Path,
    val executor: Executor = DaoConfig.DEFAULT_EXECUTOR,
    val codec: StringFormat = DaoFileConfig.DEFAULT_CODEC
)