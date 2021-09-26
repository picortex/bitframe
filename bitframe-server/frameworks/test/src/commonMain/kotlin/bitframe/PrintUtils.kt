package bitframe

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.mapper.Mapper

@OptIn(ExperimentalSerializationApi::class)
private val mapper = Mapper {
    prettyPrint = true
    encodeDefaults = true
    prettyPrintIndent = "  "
}

fun String.printJson() = printJson(this)

fun printJson(json: String) {
    println(mapper.encodeToString(mapper.decodeFromString(json)))
}