package pimonitor.core.picortex

import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import later.await
import later.later
import pimonitor.core.invites.Invite
import pimonitor.core.invites.InviteStatus

open class PiCortexDashboardDaodService(
    private val config: ServiceConfigDaod
) : PiCortexDashboardServiceCore {
    val invitesDao by lazy { config.daoFactory.get<Invite>() }
    override fun acceptInvite(params: AcceptPicortexInviteRawParams) = config.scope.later {
        val args = params.toValidatedInviteParams()
        val invite = invitesDao.load(args.inviteId).await()
        val status = InviteStatus.AcceptedDashboard(PICORTEX_DASHBOARD)
        invite.status
        args
    }
}