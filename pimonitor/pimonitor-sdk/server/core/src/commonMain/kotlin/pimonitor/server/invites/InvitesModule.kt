package pimonitor.server.invites

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.core.RestPath

class InvitesModule(
    private val controller: InvitesController
) : Module {
    override val name: String = "Invites"
    override val actions: List<Action> = listOf(
        Action("Accept Sage Invite", mapOf(), HttpRoute(HttpMethod.Post, RestPath.inviteAcceptSage) {
            controller.acceptSageInvite(it)
        }),
        Action("Accept PiCortex Invite", mapOf(), HttpRoute(HttpMethod.Post, RestPath.inviteAcceptPicortex) {
            controller.acceptPicortexInvite(it)
        })
    )
}