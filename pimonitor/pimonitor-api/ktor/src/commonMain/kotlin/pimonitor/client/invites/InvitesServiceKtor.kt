package pimonitor.client.invites

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.Later
import later.later
import pimonitor.core.RestPath
import pimonitor.core.businesses.params.InviteMessageParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.Invite
import pimonitor.core.invites.InviteInfo
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import response.decodeResponseFromString

class InvitesServiceKtor(
    override val config: ServiceConfigKtor
) : InvitesService(config) {

    private val client get() = config.http
    private val json get() = config.json

    override fun send(rb: RequestBody.Authorized<InviteToShareReportsParams>): Later<Invite> {
        TODO("Not yet implemented")
    }

    override fun defaultInviteMessage(rb: RequestBody.Authorized<InviteMessageParams>): Later<String> {
        TODO("Not yet implemented")
    }

    override fun load(rb: RequestBody.UnAuthorized<String>): Later<InviteInfo> {
        TODO("Not yet implemented")
    }

    override fun acceptSageInvite(rb: RequestBody.UnAuthorized<AcceptSageOneInviteParams>) = config.scope.later {
        val res = client.post("${config.url}/${RestPath.inviteAcceptSage}") {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(AcceptSageOneInviteParams.serializer(), res.bodyAsText()).response()
    }

    override fun acceptPiCortexInvite(rb: RequestBody.UnAuthorized<AcceptPicortexInviteParams>): Later<AcceptPicortexInviteParams> {
        TODO("Not yet implemented")
    }
}