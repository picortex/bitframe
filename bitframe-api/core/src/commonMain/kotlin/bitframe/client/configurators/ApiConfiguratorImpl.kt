package bitframe.client.configurators

class ApiConfiguratorImpl(
    override var appId: String? = null,
    override var url: String? = null,
    override var namespace: String? = "app",
    override var logging: LoggingConfigurator? = LoggingConfiguratorImpl()
) : ApiConfigurator {
    override fun logging(configurator: LoggingConfigurator.() -> Unit) {
        logging = (logging ?: LoggingConfiguratorImpl()).also(configurator)
    }
}