@file:JvmName("PropertiesUtils")

package properties

import java.io.FileInputStream
import java.io.IOException
import java.util.Properties

fun readPropertiesFromResource(path: String): Properties {
    val inputStream = ClassLoader.getSystemResourceAsStream(path) ?: throw IOException("Failed to read file from resource with path $path")
    return Properties().apply { load(inputStream) }
}

fun readPropertiesFromResourceOrNull(path: String): Properties? = runCatching {
    readPropertiesFromResource(path)
}.getOrNull()

fun readPropertiesFromFileSystem(path: String): Properties {
    val inputStream = FileInputStream(path)
    return Properties().apply { load(inputStream) }
}

fun readPropertiesFromFileSystemOrNull(path: String) = runCatching {
    readPropertiesFromFileSystem(path)
}.getOrNull()
