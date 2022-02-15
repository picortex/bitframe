package bitframe.client.configurators

import kotlin.js.JsExport

@JsExport
interface LoggingConfigurator {
    var console: Boolean?
    var sentry: Boolean?
}