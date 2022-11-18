package bitframe

import bitframe.internal.ServerConfigImpl
import koncurrent.Executor
import kotlinx.serialization.StringFormat

inline fun <S, E> ServerConfig(
    service: S,
    endpoint: E,
    executor: Executor,
    codec: StringFormat
): ServerConfig<S, E> = ServerConfigImpl(codec, service, endpoint, executor)