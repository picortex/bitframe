package pimonitor.core.invites

import bitframe.core.*
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later
import mailer.AddressInfo
import mailer.EmailDraft
import pimonitor.core.businesses.DASHBOARD_FINANCIAL
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.MonitorBusinessBasicInfo
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.params.InviteMessageParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.contacts.ContactPersonBusinessInfo
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.picortex.toValidatedInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.sage.SageApiCredentials
import pimonitor.core.sage.toValidatedInviteParams
import pimonitor.core.spaces.SPACE_TYPE

open class InvitesServiceDaod(
    open val config: ServiceConfigDaod
) : InvitesServiceCore {

    private val factory get() = config.daoFactory
    private val monitorBusinessesDao by lazy { factory.get<MonitorBusinessBasicInfo>() }
    private val monitoredBusinessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val usersDao by lazy { factory.get<User>() }
    private val spacesDao by lazy { factory.get<Space>() }
    private val invitesDao by lazy { factory.get<Invite>() }
    private val userSpaceInfoDao by lazy { factory.get<UserSpaceInfo>() }
    private val userContactsDao by lazy { CompoundDao(factory.get<UserEmail>(), factory.get<UserPhone>()) }
    private val contactPersonBusinessInfoDao by lazy { factory.get<ContactPersonBusinessInfo>() }
    private val piCortexCredentialsDao by lazy { factory.get<PiCortexApiCredentials>() }
    private val sageCredentialsDao by lazy { factory.get<SageApiCredentials>() }

    override fun send(rb: RequestBody.Authorized<InviteToShareReportsParams>): Later<Invite> = config.scope.later {
        val userContact = userContactsDao.all(
            condition = UserContact::value isEqualTo rb.data.to
        ).await().firstOrNull() ?: error("Business contact with email ${rb.data.to} is not found in pimonitor")

        val business = monitoredBusinessesDao.load(rb.data.businessId).await()
        val senderSpace = spacesDao.load(business.owningSpaceId).await()

        val inviteParams = Invite(
            invitorUserId = rb.session.user.uid,
            invitorSpaceId = rb.session.space.uid,
            invitedBusinessId = business.uid,
            invitedContactUserId = userContact.userId,
            sentInviteMessage = rb.data.message,
            status = listOf(
                InviteStatus.Sent(params = rb.data)
            )
        )
        val invite = invitesDao.create(inviteParams).await()
        val draft = EmailDraft(
            subject = rb.data.subject,
            body = "${rb.data.message}\n\nGoto <a href=https://pimonitor.vercel.app/connect/${invite.uid}>PiMonitor</a>"
        )
        config.mailer.send(
            draft = draft,
            from = AddressInfo(
                name = senderSpace.name,
                email = "support@picortex.com"
            ),
            to = listOf(
                AddressInfo(rb.data.to),
                AddressInfo(email = "support@picortex.com")
            )
        ).await()
        invite
    }

    private suspend fun business(spaceId: String): MonitorBusinessBasicInfo? {
        val space = spacesDao.load(spaceId).await()
        return if (space.type == SPACE_TYPE.COOPERATE_MONITOR) {
            monitorBusinessesDao.all(
                MonitorBusinessBasicInfo::owningSpaceId isEqualTo space.uid
            ).await().firstOrNull()
        } else null
    }

    override fun defaultInviteMessage(rb: RequestBody.Authorized<InviteMessageParams>) = config.scope.later {
        val name = business(rb.session.space.uid)?.name ?: rb.session.user.name
        PreInviteInfo(
            invitorName = name,
            inviteMessage = "$name would like to invite you to share your operational & financial reports with them through PiMonitor"
        )
    }

    override fun load(rb: RequestBody.UnAuthorized<String>): Later<InviteInfo> = config.scope.later {
        val invite = invitesDao.load(rb.data).await()
        val name = business(invite.invitorSpaceId)?.name ?: usersDao.load(invite.invitorUserId).await().name
        InviteInfo(
            inviteId = invite.uid,
            invitorName = name,
            sentInviteMessage = invite.sentInviteMessage
        )
    }

    override fun acceptSageInvite(rb: RequestBody.UnAuthorized<AcceptSageOneInviteParams>) = config.scope.later {
        val params = rb.data.toValidatedInviteParams()
        val invite = invitesDao.load(params.inviteId).await()
        val business = monitoredBusinessesDao.load(invite.invitedBusinessId).await()
        val status = InviteStatus.AcceptedFinancialDashboard(DASHBOARD_FINANCIAL.SAGE_ONE)
        val cred = SageApiCredentials(
            businessId = business.uid,
            companyId = params.companyId,
            username = params.username,
            password = params.password
        )
        sageCredentialsDao.create(cred).await()
        invitesDao.update(invite.copy(status = (invite.status + status).toInteroperableList())).await()
        monitoredBusinessesDao.update(business.copy(financialBoard = DASHBOARD_FINANCIAL.SAGE_ONE)).await()
        params
    }

    override fun acceptPiCortexInvite(rb: RequestBody.UnAuthorized<AcceptPicortexInviteParams>) = config.scope.later {
        val params = rb.data.toValidatedInviteParams()
        val invite = invitesDao.load(params.inviteId).await()
        val business = monitoredBusinessesDao.load(invite.invitedBusinessId).await()
        val status = InviteStatus.AcceptedOperationDashboard(DASHBOARD_OPERATIONAL.PICORTEX)
        val cred = PiCortexApiCredentials(
            businessId = business.uid,
            subdomain = params.subdomain,
            secret = params.secret
        )
        val credPromise = piCortexCredentialsDao.create(cred)
        val invitePromise = invitesDao.update(invite.copy(status = (invite.status + status).toInteroperableList()))
        val businessPromise = monitoredBusinessesDao.update(business.copy(operationalBoard = DASHBOARD_OPERATIONAL.PICORTEX))
        credPromise.await(); invitePromise.await(); businessPromise.await();
        params
    }
}