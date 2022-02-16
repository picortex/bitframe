import bitframe.client.configurators.SdkConfigurator
import pimonitor.client.PiMonitorReactAppScope
import pimonitor.client.configurtors.toPiMonitorAppScopeConfig

@JsExport
fun scope(configurator: SdkConfigurator): PiMonitorReactAppScope {
    val config = configurator.toPiMonitorAppScopeConfig()
    return PiMonitorReactAppScope(config)
}