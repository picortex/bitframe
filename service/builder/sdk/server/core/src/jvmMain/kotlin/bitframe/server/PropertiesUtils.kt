package bitframe.server

import java.io.File
import java.util.*

fun readPropertiesOrNull(path: String): Properties? {
    val inputStream = ClassLoader.getSystemResourceAsStream(path) ?: return null
    return Properties().apply { load(inputStream) }
}

fun properties(path: String) = readPropertiesOrNull(path) ?: error("Failed to get file $path")

fun File.properties(): Properties = Properties().apply { load(inputStream()) }

fun File.readPropertiesOrNull(path: String): Properties? = try {
    properties(path)
} catch (e: Exception) {
    null
}