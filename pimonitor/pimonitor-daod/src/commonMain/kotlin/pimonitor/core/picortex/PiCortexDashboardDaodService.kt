package pimonitor.core.picortex

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.invites.Invite
import pimonitor.core.invites.InviteStatus

open class PiCortexDashboardDaodService(
    private val config: ServiceConfigDaod
) : PiCortexDashboardServiceCore {
    private val factory get() = config.daoFactory
    private val businessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val invitesDao by lazy { factory.get<Invite>() }
    private val credentialsDao by lazy { factory.get<PiCortexApiCredentials>() }
    override fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptPicortexInviteParams>) = config.scope.later {
        val params = rb.data.toValidatedInviteParams()
        val invite = invitesDao.load(params.inviteId).await()
        val business = businessesDao.load(invite.invitedBusinessId).await()
        val status = InviteStatus.AcceptedOperationDashboard(DASHBOARD_OPERATIONAL.PICORTEX)
        val cred = PiCortexApiCredentials(
            businessId = business.uid,
            subdomain = params.subdomain,
            secret = params.secret
        )
        val credPromise = credentialsDao.create(cred)
        val invitePromise = invitesDao.update(invite.copy(status = (invite.status + status).toInteroperableList()))
        val businessPromise = businessesDao.update(business.copy(operationalBoard = DASHBOARD_OPERATIONAL.PICORTEX))
        credPromise.await(); invitePromise.await(); businessPromise.await();
        params
    }
}