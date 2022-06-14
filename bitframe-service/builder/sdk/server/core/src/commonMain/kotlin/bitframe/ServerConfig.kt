package bitframe

import kotlinx.serialization.json.Json

interface ServerConfig<out E> {
    val json: Json
    val endpoint: E

    companion object {
        operator fun <E> invoke(endpoint: E, json: Json): ServerConfig<E> = ServerConfigImpl(json, endpoint)
    }
}