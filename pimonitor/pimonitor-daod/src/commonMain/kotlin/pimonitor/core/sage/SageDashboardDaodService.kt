package pimonitor.core.sage

import akkounts.sage.SageOneZAService
import akkounts.sage.reports.SageOneZAReportsService
import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later
import pimonitor.core.businesses.DASHBOARD_FINANCIAL
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.invites.Invite
import pimonitor.core.invites.InviteStatus

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
        val status = InviteStatus.AcceptedFinancialDashboard(DASHBOARD_FINANCIAL.SAGE_ONE)
        val cred = SageApiCredentials(
            businessId = business.uid,
            companyId = params.companyId,
            username = params.username,
            password = params.password
        )
        credentialsDao.create(cred).await()
        invitesDao.update(invite.copy(status = (invite.status + status).toInteroperableList())).await()
        businessesDao.update(business.copy(financialBoard = DASHBOARD_FINANCIAL.SAGE_ONE)).await()
        params
    }
}