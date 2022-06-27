package bitframe.api

import bitframe.ApiConfigKuest
import bitframe.api.internal.ApiConfigKuestImpl

inline fun <E1, E2> ApiConfigKuest<E1>.map(transform: (E1) -> E2): ApiConfigKuest<E2> = ApiConfigKuestImpl(
    appId, session, cache, bus, logger, executor, http, transform(endpoint), codec
)