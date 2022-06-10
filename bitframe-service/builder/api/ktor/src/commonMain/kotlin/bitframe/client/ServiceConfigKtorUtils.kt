package bitframe.client

fun <E1, E2> ServiceConfigKtor<E1>.map(transform: (E1) -> E2): ServiceConfigKtor<E2> = ServiceConfigKtorImpl(
    appId, url, transform(endpoint), cache, http, session, bus, logger, scope, json
)