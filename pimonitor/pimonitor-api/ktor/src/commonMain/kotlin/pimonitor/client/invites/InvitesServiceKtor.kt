package pimonitor.client.invites

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.businesses.params.InviteMessageParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.Invite
import pimonitor.core.invites.InviteInfo
import pimonitor.core.invites.PreInviteInfo
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import response.decodeResponseFromString

class InvitesServiceKtor(
    override val config: ServiceConfigKtor
) : InvitesService(config) {

    private val client get() = config.http
    private val json get() = config.json

    override fun send(rb: RequestBody.Authorized<InviteToShareReportsParams>) = config.scope.later {
        val res = client.post(config.pathV1.invitesSend) {
            setBody(json.of(rb))
        }
        val text = res.bodyAsText()
        println(text)
        json.decodeResponseFromString(Invite.serializer(), text).response()
    }

    override fun defaultInviteMessage(rb: RequestBody.Authorized<InviteMessageParams>) = config.scope.later {
        val res = client.post(config.pathV1.invitesDefaultMessage) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(PreInviteInfo.serializer(), res.bodyAsText()).response()
    }

    override fun load(rb: RequestBody.UnAuthorized<String>) = config.scope.later {
        val res = client.post(config.pathV1.invitesLoad) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(InviteInfo.serializer(), res.bodyAsText()).response()
    }

    override fun acceptSageInvite(rb: RequestBody.UnAuthorized<AcceptSageOneInviteParams>) = config.scope.later {
        val res = client.post(config.pathV1.invitesAcceptSage) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(AcceptSageOneInviteParams.serializer(), res.bodyAsText()).response()
    }

    override fun acceptPiCortexInvite(rb: RequestBody.UnAuthorized<AcceptPicortexInviteParams>) = config.scope.later {
        val res = client.post(config.pathV1.invitesAcceptPicortex) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(AcceptPicortexInviteParams.serializer(), res.bodyAsText()).response()
    }
}