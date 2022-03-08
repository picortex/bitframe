package pimonitor.core.sage

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
import pimonitor.core.picortex.PICORTEX_DASHBOARD

open class SageDashboardDaodService(
    private val config: ServiceConfigDaod
) : SageDashboardServiceCore {
    private val factory get() = config.daoFactory
    private val businessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val invitesDao by lazy { factory.get<Invite>() }
    private val credentialsDao by lazy { factory.get<SageApiCredentials>() }
    override fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptSageOneInviteParams>) = config.scope.later {
        val params = rb.data.toValidatedInviteParams()
        val invite = invitesDao.load(params.inviteId).await()
        val business = businessesDao.load(invite.invitedBusinessId).await()
        val status = InviteStatus.AcceptedDashboard(PICORTEX_DASHBOARD)
        val cred = SageApiCredentials()
        val credPromise = credentialsDao.create(cred)
        val invitePromise = invitesDao.update(invite.copy(status = (invite.status + status).toInteroperableList()))
        val businessPromise = businessesDao.update(business.copy(dashboard = DASHBOARD_OPERATIONAL.PICORTEX))
        credPromise.await(); invitePromise.await(); businessPromise.await();
        params
    }
}