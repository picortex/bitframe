package bitframe

import bitframe.internal.ServerConfigImpl

inline fun <E1, E2> ServerConfig<E1>.map(transform: (E1) -> E2): ServerConfig<E2> {
    return ServerConfigImpl(json, transform(endpoint))
}