@file:JsExport

package bitframe.monitors

import bitframe.authentication.users.UserRef
import identifier.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class IndividualMonitor(
    override val uid: String,
    override val name: String,
    val email: Email,
    val userRef: UserRef
) : Monitor()