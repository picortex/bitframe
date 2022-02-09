@file:JsExport

package pimonitor.monitors

import bitframe.actors.users.UserRef
import identifier.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class IndividualMonitor(
    override val uid: String,
    override val name: String,
    val email: Email,
    val userRef: UserRef
) : Monitor() {
    override fun copyId(id: String) = copy(uid = id)
}