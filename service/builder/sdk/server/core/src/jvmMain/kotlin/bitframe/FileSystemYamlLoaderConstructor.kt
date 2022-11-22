package bitframe

import bitframe.exceptions.IllegalConfiguration
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import okio.FileSystem

inline fun <reified C : ServerConfiguration> FileSystemYmlLoader(
    serializer: KSerializer<C> = serializer(),
    verbose: Boolean = false
): ConfigLoader<C> {
    val appRoot = System.getenv("APP_ROOT") ?: throw MISSING_ENV_APP_ROOT
    if (verbose) println("APP_ROOT: $appRoot")
    val config = System.getenv("CONFIG") ?: throw MISSING_ENV_CONFIG
    if (verbose) println("CONFIG: $config")
    return FileSystemYmlLoader(fs = FileSystem.SYSTEM, appRoot, config, serializer)
}

@PublishedApi
internal val MISSING_ENV_CONFIG = IllegalConfiguration(
    """
    Missing Config environment variable.
    FIX TIP: Launch the application with CONFIG environment variable
""".trimIndent()
)

@PublishedApi
internal val MISSING_ENV_APP_ROOT = IllegalConfiguration(
    """
    Missing APP_ROOT environment variable.
    FIX TIP: Launch the application with APP_ROOT environment variable
""".trimIndent()
)