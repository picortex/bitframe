package bitframe

import bitframe.internal.ServerConfigImpl
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json

interface ServerConfig<out E> {
    val codec: StringFormat
    val endpoint: E

    companion object {
        operator fun <E> invoke(endpoint: E, codec: StringFormat): ServerConfig<E> = ServerConfigImpl(codec, endpoint)
    }
}