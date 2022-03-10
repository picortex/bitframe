@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.invites

import bitframe.core.RequestBody
import later.Later
import pimonitor.core.businesses.params.InviteMessageParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmSynthetic

@JsExport
interface InvitesServiceCore {

    @JsName("_ignore_invite")
    @JvmSynthetic
    fun send(rb: RequestBody.Authorized<InviteToShareReportsParams>): Later<Invite>

    @JsName("_ignore_inviteMessage")
    @JvmSynthetic
    fun defaultInviteMessage(rb: RequestBody.Authorized<InviteMessageParams>): Later<PreInviteInfo>

    @JsName("_ignore_loadInvite")
    @JvmSynthetic
    fun load(rb: RequestBody.UnAuthorized<String>): Later<InviteInfo>

    @JsName("_ignore_acceptSageInvite")
    @JvmSynthetic
    fun acceptSageInvite(rb: RequestBody.UnAuthorized<AcceptSageOneInviteParams>): Later<AcceptSageOneInviteParams>

    @JsName("_ignore_acceptPicortexInvite")
    @JvmSynthetic
    fun acceptPiCortexInvite(rb: RequestBody.UnAuthorized<AcceptPicortexInviteParams>): Later<AcceptPicortexInviteParams>
}