package bitframe.api

import bitframe.ApiConfigKtor
import bitframe.api.internal.ApiConfigKtorImpl

inline fun <E1, E2> ApiConfigKtor<E1>.map(transform: (E1) -> E2): ApiConfigKtor<E2> = ApiConfigKtorImpl(
    appId, session, cache, bus, logger, executor, http, transform(endpoint), codec
)