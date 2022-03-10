@file:JsExport

package pimonitor.core.invites

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class InviteInfo(
    val inviteId: String,
    val invitorName: String,
    val inviteeName: String,
    val sentInviteMessage: String
)