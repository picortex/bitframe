package akkounts

import java.io.IOException
import java.util.*

fun readPropertiesFromResource(path: String): Properties {
    val inputStream = ClassLoader.getSystemResourceAsStream(path) ?: throw IOException("Failed to read file from resource with path $path")
    return Properties().apply { load(inputStream) }
}

fun readPropertiesFromResourceOrNull(path: String): Properties? = runCatching {
    readPropertiesFromResource(path)
}.getOrNull()