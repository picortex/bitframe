package bitframe

import kotlinx.serialization.json.Json

class ServerConfigImpl<out E>(
    override val json: Json,
    override val endpoint: E
) : ServerConfig<E>