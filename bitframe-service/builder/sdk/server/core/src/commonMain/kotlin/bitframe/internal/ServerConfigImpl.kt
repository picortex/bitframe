package bitframe.internal

import bitframe.ServerConfig
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json

class ServerConfigImpl<out E>(
    override val codec: StringFormat,
    override val endpoint: E
) : ServerConfig<E>