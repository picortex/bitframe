@file:JsExport

package pimonitor.monitors

import contacts.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class Monitor {
    abstract val uid: String
    abstract val name: String
    abstract val email: Email
}
