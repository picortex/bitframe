package bitframe.api

import bitframe.ApiConfigRest
import bitframe.api.internal.ApiConfigRestImpl

inline fun <E1, E2, H> ApiConfigRest<E1, H>.map(
    transform: (E1) -> E2
): ApiConfigRest<E2, H> = ApiConfigRestImpl(transform(endpoint), http, codec)