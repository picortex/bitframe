package bitframe.core

import kotlinx.serialization.json.Json

@Deprecated("In favour of ApiConfigRest")
interface ServiceConfigRest<out E> : ServiceConfig {
    val endpoint: E
    val json: Json

    companion object {
        val DEFAULT_JSON: Json = Json
    }
}