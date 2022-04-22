package bitframe.client.configurators

import logging.Appender
import logging.ConsoleAppender
import logging.Logger

fun ApiConfigurator.toApiMode(): ApiMode {
    val appenders = buildList<Appender> {
        val l = logging ?: LoggingConfiguratorImpl()
        if (l.console == true) add(ConsoleAppender())
    }
    val logger = Logger(*appenders.toTypedArray())
    val ns = namespace ?: ApiConfigurator.DEFAULT_NAMESPACE
    return if (appId?.isNotEmpty() == true && url?.isNotEmpty() == true) {
        ApiMode.Live(namespace = ns, appId = appId!!, url = url!!, logger = logger)
    } else {
        ApiMode.Mock(namespace = ns, logger = logger)
    }
}

fun ApiConfigurator.toValidApiConfigurator(): ApiConfiguratorImpl {
    val conf = ApiConfiguratorImpl()
    conf.appId = appId
    conf.url = url
    conf.logging = logging ?: LoggingConfiguratorImpl()
    conf.namespace = namespace
    return conf
}