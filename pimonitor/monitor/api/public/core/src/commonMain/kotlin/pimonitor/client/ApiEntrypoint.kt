package pimonitor.client

import bitframe.client.configurators.*

fun api(configurator: ApiConfigurator.() -> Unit): MonitorApi {
    val cfg = ApiConfiguratorImpl().also(configurator).toValidApiConfigurator()
    return cfg.toApiMode().toPiMonitorApi()
}