package pimonitor.server.invites

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class InvitesModule(
    private val controller: InvitesController
) : Module {
    private val config get() = controller.service.config
    override val name: String = "Invites"
    override val actions: List<Action> = listOf(
        Action("Accept Sage Invite", mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.invitesAcceptSage) {
            controller.acceptSageInvite(it)
        }),
        Action("Accept PiCortex Invite", mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.invitesAcceptPicortex) {
            controller.acceptPicortexInvite(it)
        }),
        Action("Send Invite", mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.invitesSend) {
            controller.send(it)
        }),
        Action("Load Invite", mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.invitesLoad) {
            controller.load(it)
        }),
        Action("Default Invite Message", mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.invitesDefaultMessage) {
            controller.defaultInviteMessage(it)
        }),
    )
}