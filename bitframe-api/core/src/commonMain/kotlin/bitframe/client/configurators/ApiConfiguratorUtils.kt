package bitframe.client.configurators

fun ApiConfigurator.toApiMode() = if (appId?.isNotEmpty() == true && url?.isNotEmpty() == true) {
    ApiMode.Live(appId!!, url!!)
} else ApiMode.Mock()