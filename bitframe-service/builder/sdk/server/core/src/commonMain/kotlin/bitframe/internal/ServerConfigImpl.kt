package bitframe.internal

import bitframe.ServerConfig
import kotlinx.serialization.json.Json

class ServerConfigImpl<out E>(
    override val json: Json,
    override val endpoint: E
) : ServerConfig<E>