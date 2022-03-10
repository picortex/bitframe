package bitframe.client

import bitframe.client.configurators.ApiMode

fun ApiMode.Mock.toApiConfigMock() = BitframeApiMockConfig(
    appId = appId,
    cache = cache,
    logger = logger,
    scope = scope,
)