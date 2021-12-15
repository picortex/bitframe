@file:JsExport

package pimonitor.integrations

import kotlin.js.JsExport

data class PiCortexUserCredentials(
    val secret: String,
    val subdomain: String
)