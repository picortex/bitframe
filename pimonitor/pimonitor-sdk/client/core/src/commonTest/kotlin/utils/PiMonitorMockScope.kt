package utils

import bitframe.client.BitframeViewModelConfig
import pimonitor.client.PiMonitorApiMock
import pimonitor.client.PiMonitorAppScope
import pimonitor.client.PiMonitorAppScopeConfig

fun PiMonitorMockScope(): PiMonitorAppScope {
    val service = PiMonitorApiMock()
    val config = PiMonitorAppScopeConfig(
        service, BitframeViewModelConfig(
            recoveryTime = 0L
        )
    )
    return PiMonitorAppScope(config)
}