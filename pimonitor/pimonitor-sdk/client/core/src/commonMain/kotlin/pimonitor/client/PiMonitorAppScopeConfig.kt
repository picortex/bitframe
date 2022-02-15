package pimonitor.client

import bitframe.client.BitframeAppScopeConfig
import bitframe.client.BitframeViewModelConfig

class PiMonitorAppScopeConfig(
    override val api: PiMonitorApi,
    override val viewModel: BitframeViewModelConfig
) : BitframeAppScopeConfig