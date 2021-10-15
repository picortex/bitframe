@file:JsExport

package pimonitor.monitors

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class Monitor {
    abstract val uid: String
    abstract val name: String
}
