package bitframe

import bitframe.internal.ServerConfigImpl

inline fun <S, E1, E2> ServerConfig<S, E1>.mapEndpoint(transform: E1.() -> E2): ServerConfig<S, E2> {
    return ServerConfigImpl(codec, service, transform(endpoint), executor)
}

inline fun <S1, S2, E> ServerConfig<S1, E>.mapService(transform: S1.() -> S2): ServerConfig<S2, E> {
    return ServerConfigImpl(codec, transform(service), endpoint, executor)
}

inline fun <S1, E1, S2, E2> ServerConfig<S1, E1>.map(transform: S1.(E1) -> Pair<S2, E2>): ServerConfig<S2, E2> {
    val (s, e) = transform(service, endpoint)
    return ServerConfigImpl(codec, s, e, executor)
}