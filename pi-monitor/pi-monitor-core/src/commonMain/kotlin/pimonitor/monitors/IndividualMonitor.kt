@file:JsExport

package pimonitor.monitors

import contacts.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class IndividualMonitor(
    override val uid: String,
    override val name: String,
    override val email: Email
) : Monitor()