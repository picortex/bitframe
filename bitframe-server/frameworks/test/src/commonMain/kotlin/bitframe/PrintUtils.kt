package bitframe

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.mapper.Mapper
import kotlin.jvm.JvmName
import kotlin.jvm.JvmSynthetic

@OptIn(ExperimentalSerializationApi::class)
private val mapper = Mapper {
    prettyPrint = true
    encodeDefaults = true
    prettyPrintIndent = "  "
}

@JvmName("printAsJson")
fun String.printJson() = printJson(this)

fun printJson(json: String) {
    println(mapper.encodeToString(mapper.decodeFromString(json)))
}