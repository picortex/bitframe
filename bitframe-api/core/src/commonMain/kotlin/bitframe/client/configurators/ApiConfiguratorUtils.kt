package bitframe.client.configurators

import logging.Appender
import logging.ConsoleAppender
import logging.Logger

fun ApiConfigurator.toApiMode(): ApiMode {
    val appenders = buildList<Appender> {
        if (logging?.console == true) add(ConsoleAppender())
    }
    val logger = Logger(*appenders.toTypedArray())
    return if (appId?.isNotEmpty() == true && url?.isNotEmpty() == true) {
        ApiMode.Live(
            namespace = namespace ?: ApiConfigurator.DEFAULT_NAMESPACE,
            appId = appId!!,
            url = url!!,
            logger = logger
        )
    } else {
        ApiMode.Mock(
            namespace = namespace ?: ApiConfigurator.DEFAULT_NAMESPACE,
            logger = logger
        )
    }
}

fun ApiConfigurator.toValidApiConfigurator(): ApiConfiguratorImpl {
    val conf = ApiConfiguratorImpl()
    conf.appId = appId
    conf.url = url
    conf.logging = logging
    return conf
}