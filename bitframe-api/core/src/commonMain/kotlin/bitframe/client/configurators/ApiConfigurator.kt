package bitframe.client.configurators

interface ApiConfigurator {
    var appId: String?
    var url: String?
    var logging: LoggingConfigurator?
    fun logging(configurator: LoggingConfigurator.() -> Unit)
}