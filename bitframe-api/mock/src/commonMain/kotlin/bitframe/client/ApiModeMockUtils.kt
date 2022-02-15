package bitframe.client

import bitframe.client.configurators.ApiMode

fun ApiMode.Mock.toMockApiConfig() = BitframeApiMockConfig(
    appId = appId,
    cache = cache,
    logger = logger,
    scope = scope,
)