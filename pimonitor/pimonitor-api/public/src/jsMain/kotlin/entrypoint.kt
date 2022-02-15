import bitframe.client.configurators.ApiConfigurator
import bitframe.client.configurators.toApiMode
import bitframe.client.configurators.toValidConfigurator
import pimonitor.client.PiMonitorApi
import pimonitor.client.toPiMonitorApi

fun api(configurator: ApiConfigurator): PiMonitorApi {
    val cfg = configurator.toValidConfigurator()
    return cfg.toApiMode().toPiMonitorApi()
}