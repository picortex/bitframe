package bitframe.internal

import bitframe.ServerConfig
import koncurrent.Executor
import kotlinx.serialization.StringFormat

class ServerConfigImpl<out S, out E>(
    override val codec: StringFormat,
    override val service: S,
    override val endpoint: E,
    override val executor: Executor
) : ServerConfig<S, E>