@file:JsExport

package pimonitor.core.invites

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class PreInviteInfo(
    val invitorName: String,
    val inviteMessage: String
)