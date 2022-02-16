import bitframe.client.configurators.SdkConfigurator
import pimonitor.client.PiMonitorReactAppScope
import pimonitor.client.configurtors.toBitframeAppScopeConfig

@JsExport
fun scope(configurator: SdkConfigurator): PiMonitorReactAppScope {
    val config = configurator.toBitframeAppScopeConfig()
    return PiMonitorReactAppScope(config)
}