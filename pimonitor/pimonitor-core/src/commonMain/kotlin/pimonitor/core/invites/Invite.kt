@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.invites

import bitframe.core.Savable
import bitframe.core.UNSET
import bitframe.core.UserContact
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Invite(
    val invitorUserId: String,
    val invitorSpaceId: String,
    val invitedBusinessId: String,
    val invitedContactUserId: String,
    val sentInviteMessage: String,
    val status: List<InviteStatus>,
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}