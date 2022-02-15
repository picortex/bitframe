package pimonitor.client

import bitframe.client.configurators.SdkConfigurator
import pimonitor.client.configurtors.toPiMonitorAppScopeConfig

fun scope(configurator: SdkConfigurator): PiMonitorReactAppScope {
    val config = configurator.toPiMonitorAppScopeConfig()
    return PiMonitorReactAppScope(config)
}