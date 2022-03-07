package pimonitor.core.picortex

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later
import pimonitor.core.invites.Invite
import pimonitor.core.invites.InviteStatus

open class PiCortexDashboardDaodService(
    private val config: ServiceConfigDaod
) : PiCortexDashboardServiceCore {
    val invitesDao by lazy { config.daoFactory.get<Invite>() }
    val credentialsDao by lazy { config.daoFactory.get<PiCortexApiCredentials>() }
    override fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptPicortexInviteRawParams>) = config.scope.later {
        val params = rb.data.toValidatedInviteParams()
        val invite = invitesDao.load(params.inviteId).await()
        val status = InviteStatus.AcceptedDashboard(PICORTEX_DASHBOARD)
        val cred = PiCortexApiCredentials(
            businessId = invite.invitedBusinessId,
            subdomain = params.subdomain,
            secret = params.secret
        )
        val credPromise = credentialsDao.create(cred)
        val invitePromise = invitesDao.update(invite.copy(status = (invite.status + status).toInteroperableList()))
        credPromise.await(); invitePromise.await();
        params
    }
}