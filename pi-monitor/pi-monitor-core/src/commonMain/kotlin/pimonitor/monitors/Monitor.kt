@file:JsExport

package pimonitor.monitors

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.js.JsExport

@Serializable
sealed class Monitor {
    abstract val uid: String
    abstract val name: String

    fun ref() = MonitorRef(uid, name)
}
