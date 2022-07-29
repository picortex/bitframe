package bitframe

import koncurrent.Executor
import okio.FileSystem
import kotlin.jvm.JvmOverloads

class DaoFactoryFileConfig @JvmOverloads constructor(
    val fs: FileSystem,
    val executor: Executor = DaoConfig.DEFAULT_EXECUTOR
)