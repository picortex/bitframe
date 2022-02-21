@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client.configurators

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface ApiConfigurator {
    var appId: String?
    var url: String?
    var namespace: String?
    var logging: LoggingConfigurator?

    companion object {
        val DEFAULT_NAMESPACE: String = "app"
    }

    @JsName("${'$'}logging_ignore_")
    fun logging(configurator: LoggingConfigurator.() -> Unit)
}