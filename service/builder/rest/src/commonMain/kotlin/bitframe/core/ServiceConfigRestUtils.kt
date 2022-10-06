package bitframe.core

inline fun <E1, E2> ServiceConfigRest<E1>.map(
    transform: (E1) -> E2
): ServiceConfigRest<E2> = ServiceConfigRestImpl(
    endpoint = transform(endpoint),
    logger = logger,
    bus = bus,
    scope = scope,
    json = json
)