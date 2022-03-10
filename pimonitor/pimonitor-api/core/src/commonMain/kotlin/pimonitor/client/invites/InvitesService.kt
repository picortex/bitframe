@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.invites

import bitframe.client.ServiceConfig
import bitframe.client.getSignedInSessionTo
import bitframe.core.RequestBody
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.params.*
import pimonitor.core.invites.Invite
import pimonitor.core.invites.InviteInfo
import pimonitor.core.invites.InvitesServiceCore
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.picortex.AcceptPicortexInviteRawParams
import pimonitor.core.picortex.toValidatedInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.sage.AcceptSageOneInviteRawParams
import pimonitor.core.sage.toValidatedInviteParams
import kotlin.js.JsExport
import kotlin.js.JsName

abstract class InvitesService(
    open val config: ServiceConfig
) : InvitesServiceCore {

    fun send(params: InviteToShareReportsRawParams): Later<Invite> = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("send invite to ${params.to}"),
            data = params.toValidatedInviteToShareReportParams()
        )
        send(rb).await()
    }

    fun defaultInviteMessage(params: InviteMessageRawParams): Later<String> = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.getSignedInSessionTo("retrieve the default message"),
            data = params.toValidatedInviteMessageParams()
        )
        defaultInviteMessage(rb).await()
    }

    fun load(uid: String): Later<InviteInfo> = config.scope.later {
        val rb = RequestBody.UnAuthorized(
            appId = config.appId,
            data = uid
        )
        load(rb).await()
    }

    @JsName("acceptSageOneInvite")
    fun accept(params: AcceptSageOneInviteRawParams): Later<AcceptSageOneInviteParams> = config.scope.later {
        val rb = RequestBody.UnAuthorized(
            appId = config.appId,
            data = params.toValidatedInviteParams()
        )
        acceptSageInvite(rb).await()
    }

    @JsName("acceptPiCortexInvite")
    fun accept(params: AcceptPicortexInviteRawParams): Later<AcceptPicortexInviteParams> = config.scope.later {
        val rb = RequestBody.UnAuthorized(
            appId = config.appId,
            data = params.toValidatedInviteParams()
        )
        acceptPiCortexInvite(rb).await()
    }
}