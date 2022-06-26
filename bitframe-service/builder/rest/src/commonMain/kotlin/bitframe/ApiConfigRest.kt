package bitframe

import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmField

interface ApiConfigRest<out E, out H> {
    val endpoint: E
    val codec: StringFormat
    val http: H

    companion object {
        @JvmField
        val DEFAULT_CODEC: StringFormat = Json {
            ignoreUnknownKeys = true
        }
    }
}