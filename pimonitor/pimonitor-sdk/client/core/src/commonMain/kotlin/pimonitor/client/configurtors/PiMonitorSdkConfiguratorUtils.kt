package pimonitor.client.configurtors

import bitframe.client.BitframeAppScopeConfig
import bitframe.client.configurators.SdkConfigurator
import bitframe.client.configurators.toApiMode
import bitframe.client.configurators.toValidApiConfigurator
import bitframe.client.configurators.toViewModelConfig
import pimonitor.client.PiMonitorApi
import pimonitor.client.toPiMonitorApi

fun SdkConfigurator.toBitframeAppScopeConfig(): BitframeAppScopeConfig<PiMonitorApi> {
    val apiConfigurator = toValidApiConfigurator()
    val apiMode = apiConfigurator.toApiMode()
    return BitframeAppScopeConfig(
        api = apiMode.toPiMonitorApi(),
        viewModel = toViewModelConfig(apiMode)
    )
}