package utils

import bitframe.client.BitframeAppScopeConfig
import bitframe.client.BitframeViewModelConfig
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiMock
import pimonitor.client.PiMonitorAppScope

fun PiMonitorMockScope(): PiMonitorAppScope {
    val api: PiMonitorApi = PiMonitorApiMock()
    return PiMonitorAppScope(
        BitframeAppScopeConfig(
            api = api,
            viewModel = BitframeViewModelConfig(
                recoveryTime = 0L
            )
        )
    )
}