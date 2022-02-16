import bitframe.client.configurators.ApiConfigurator
import bitframe.client.configurators.toApiMode
import bitframe.client.configurators.toValidApiConfigurator
import pimonitor.client.PiMonitorApi
import pimonitor.client.toPiMonitorApi

@JsExport
fun api(configurator: ApiConfigurator): PiMonitorApi {
    val cfg = configurator.toValidApiConfigurator()
    return cfg.toApiMode().toPiMonitorApi()
}