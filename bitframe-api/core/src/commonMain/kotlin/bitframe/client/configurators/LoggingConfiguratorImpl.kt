package bitframe.client.configurators

class LoggingConfiguratorImpl(
    override var console: Boolean? = true,
    override var sentry: Boolean? = false
) : LoggingConfigurator