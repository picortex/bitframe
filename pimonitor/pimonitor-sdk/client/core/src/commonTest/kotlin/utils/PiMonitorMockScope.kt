package utils

import bitframe.client.BitframeAppScopeConfig
import bitframe.client.BitframeViewModelConfig
import pimonitor.client.*

fun PiMonitorTestScope() = PiMonitorAppScope(
    BitframeAppScopeConfig(
        api = PiMonitorApiTest(),
        viewModel = BitframeViewModelConfig(
            recoveryTime = 0L
        )
    )
)