package integration

import cache.MockCache
import pimonitor.client.PiMonitorServiceKtorConfig

val DEV_KTOR_CLIENT_CONFIG = PiMonitorServiceKtorConfig(
    url = "http://localhost:8080",
    appId = "<test>",
    cache = MockCache()
)

val CONFIG_UNDER_TEST = DEV_KTOR_CLIENT_CONFIG