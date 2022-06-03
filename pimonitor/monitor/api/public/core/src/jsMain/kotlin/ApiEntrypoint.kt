import bitframe.client.configurators.ApiConfigurator
import bitframe.client.configurators.toApiMode
import bitframe.client.configurators.toValidApiConfigurator
import pimonitor.client.MonitorApi
import pimonitor.client.toPiMonitorApi

@JsExport
fun api(configurator: ApiConfigurator): MonitorApi {
    val cfg = configurator.toValidApiConfigurator()
    return cfg.toApiMode().toPiMonitorApi()
}