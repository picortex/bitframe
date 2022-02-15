package pimonitor.client.configurtors

import bitframe.client.configurators.SdkConfigurator
import bitframe.client.configurators.toApiMode
import bitframe.client.configurators.toValidApiConfigurator
import bitframe.client.configurators.toViewModelConfig
import pimonitor.client.PiMonitorAppScopeConfig
import pimonitor.client.toPiMonitorApi

fun SdkConfigurator.toPiMonitorAppScopeConfig(): PiMonitorAppScopeConfig {
    val apiConfigurator = toValidApiConfigurator()
    val apiMode = apiConfigurator.toApiMode()
    return PiMonitorAppScopeConfig(
        api = apiMode.toPiMonitorApi(),
        viewModel = toViewModelConfig(apiMode)
    )
}