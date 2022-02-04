@file:JsExport

package pimonitor.monitors

import bitframe.modal.HasId
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class Monitor : HasId {
    abstract val name: String

    fun ref() = MonitorRef(uid, name)
}
