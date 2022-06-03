package pimonitor.client.config

import bitframe.client.configurators.ApiMode
import pimonitor.client.MonitorApiConfigMock

fun ApiMode.Mock.toMonitorApiConfigMock() = MonitorApiConfigMock(
    appId = appId,
    cache = cache,
    logger = logger,
    scope = scope,
)