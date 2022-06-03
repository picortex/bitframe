package pimonitor.client

import bitframe.client.configurators.ApiConfigurator
import bitframe.client.configurators.ApiConfiguratorImpl
import bitframe.client.configurators.toApiMode
import bitframe.client.configurators.toValidApiConfigurator
import kotlin.jvm.JvmStatic

class PiMonitorApiBuilder {

    companion object {
        @JvmStatic
        fun build(func: BuilderFunction<ApiConfigurator>): MonitorApi {
            val config: ApiConfigurator = ApiConfiguratorImpl()
            func.execute(config)
            return config.toValidApiConfigurator().toApiMode().toPiMonitorApi()
        }
    }

    private val config: ApiConfigurator = ApiConfiguratorImpl()

    fun build(): MonitorApi = config.toApiMode().toPiMonitorApi()

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