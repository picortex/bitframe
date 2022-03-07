package pimonitor.client

import bitframe.client.configurators.ApiConfigurator
import bitframe.client.configurators.ApiConfiguratorImpl
import bitframe.client.configurators.toApiMode

class PiMonitorApiBuilder {

    private val config: ApiConfigurator = ApiConfiguratorImpl()

    fun build(): PiMonitorApi = config.toApiMode().toPiMonitorApi()

    fun setAppId(value: String): PiMonitorApiBuilder {
        config.appId = value
        return this
    }

    fun setUrl(value: String): PiMonitorApiBuilder {
        config.url = value
        return this
    }

    fun setNamespace(value: String): PiMonitorApiBuilder {
        config.namespace = value
        return this
    }
}