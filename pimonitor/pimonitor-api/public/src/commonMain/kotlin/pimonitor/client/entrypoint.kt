package pimonitor.client

import bitframe.client.configurators.*
import bitframe.client.toKtorApiConfig
import bitframe.client.toMockApiConfig

fun api(configurator: ApiConfigurator.() -> Unit): PiMonitorApi {
    val cfg = ApiConfiguratorImpl().also(configurator).toValidConfigurator()
    return cfg.toApiMode().toPiMonitorApi()
}