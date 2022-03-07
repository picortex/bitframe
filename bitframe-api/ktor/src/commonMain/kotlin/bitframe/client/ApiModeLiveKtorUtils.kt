package bitframe.client

import bitframe.client.configurators.ApiMode

fun ApiMode.Live.toApiConfigKtor() = BitframeApiKtorConfig(
    appId = appId,
    url = url,
    cache = cache,
    json = json,
    logger = logger,
    scope = scope
)