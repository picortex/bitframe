@file:JsExport

package pimonitor.monitors

import bitframe.authentication.users.UserRef
import bitframe.modal.HasId
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
    override fun copy(id: String) = copy(uid = id)
}