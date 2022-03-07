package pimonitor.core.businesses

import bitframe.core.*
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.mutableListOf
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later
import mailer.AddressInfo
import mailer.EmailDraft
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.*
import pimonitor.core.contacts.ContactPersonBusinessInfo
import pimonitor.core.invites.Invite
import pimonitor.core.invites.InviteStatus
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.picortex.PiCortexDashboardProvider
import pimonitor.core.picortex.PiCortexDashboardProviderConfig
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE

open class BusinessesDaodService(
    open val config: ServiceConfigDaod
) : BusinessesServiceCore,
    RegisterUserUseCase by RegisterUserUseCaseImpl(config) {

    private val factory get() = config.daoFactory
    private val businessDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val spacesDao by lazy { factory.get<Space>() }
    private val invitesDao by lazy { factory.get<Invite>() }
    private val userSpaceInfoDao by lazy { factory.get<UserSpaceInfo>() }
    private val userContactsDao by lazy { CompoundDao(factory.get<UserEmail>(), factory.get<UserPhone>()) }
    private val contactPersonBusinessInfoDao by lazy { factory.get<ContactPersonBusinessInfo>() }
    private val piCortexCredentialsDao by lazy { factory.get<PiCortexApiCredentials>() }
    private val piCortexDashboardProvider by lazy {
        val cfg = PiCortexDashboardProviderConfig(json = config.json, scope = config.scope)
        PiCortexDashboardProvider(cfg)
    }
    private val logger
        get() = config.logger.with(
            "source" to this::class.simpleName
        )

    override fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>) = config.scope.later {
        val params = rb.data.toValidatedCreateBusinessParams()
        val registered = userContactsDao.all(
            condition = UserContact::value isEqualTo params.contactEmail
        ).await().firstOrNull()

        val (userId, spaceId) = if (registered == null) {
            val res1 = register(params.toRegisterUserParams()).await()
            res1.user.uid to res1.spaces.first().uid
        } else {
            val space = spacesDao.create(Space(name = params.businessName, type = SPACE_TYPE.MONITORED)).await()
            val usi = UserSpaceInfo(
                userId = registered.userId,
                spaceId = space.uid,
                type = USER_TYPE.CONTACT
            )
            userSpaceInfoDao.create(usi).await()
            registered.userId to space.uid
        }

        val business = businessDao.create(
            MonitoredBusinessBasicInfo(
                name = params.businessName,
                owningSpaceId = rb.session.space.uid
            )
        ).await()
        val cpbi = contactPersonBusinessInfoDao.create(
            ContactPersonBusinessInfo(
                userId = userId,
                businessId = business.uid,
                position = ""
            )
        ).await()
        CreateMonitoredBusinessResult(
            params = params,
            business = business,
            contact = cpbi
        )
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo rb.session.space.uid
        businessDao.all(condition).await().filter { !it.deleted }.toTypedArray().map {
            summaryOf(it)
        }.toInteroperableList()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<MonitoredBusinessBasicInfo>> = config.scope.later {
        val list = mutableListOf<MonitoredBusinessBasicInfo>()
        for (business in rb.data) list.add(businessDao.delete(business).await())
        list
    }

    private suspend fun summaryOf(business: MonitoredBusinessBasicInfo): MonitoredBusinessSummary {
        val contacts = contactPersonBusinessInfoDao.all(ContactPersonBusinessInfo::businessId isEqualTo business.uid).await().flatMap {
            userContactsDao.all(UserContact::userId isEqualTo it.userId).await()
        }.toInteroperableList()
        val invites = invitesDao.all(Invite::invitedBusinessId isEqualTo business.uid).await()
        return when (business.dashboard) {
            DASHBOARD.NONE -> MonitoredBusinessSummary.UnConnectedDashboard(
                uid = business.uid,
                name = business.name,
                contacts = contacts,
                invites = invites,
                interventions = "0 of 0"
            )
            DASHBOARD.PICORTEX -> {
                val cred = piCortexCredentialsDao.all(
                    condition = PiCortexApiCredentials::businessId isEqualTo business.uid
                ).await().first()
                val dashboard = piCortexDashboardProvider.technicalDashboardOf(cred).await()
                TODO()
//                MonitoredBusinessSummary.ConnectedDashboard(
//                    uid = business.uid,
//                    name = space.name,
//                    dashboard = DASHBOARD.PICORTEX,
//                    contacts = contacts,
//                    invites = invites,
//                    revenue = ChangeBox(),
//                    interventions = "0 of 0"
//                )
            }
            else -> {
                TODO()
            }
        }
    }

    override fun invite(rb: RequestBody.Authorized<InviteToShareReportsParams>): Later<Invite> = config.scope.later {
        val userContact = userContactsDao.all(
            condition = UserContact::value isEqualTo rb.data.to
        ).await().firstOrNull() ?: error("Business contact with email ${rb.data.to} is not found in pimonitor")

//        val contactBusinessInfo = contactPersonBusinessInfoDao.all(
//            condition = ContactPersonBusinessInfo::userId isEqualTo userContact.userId
//        ).await().firstOrNull() ?: error("User with email ${rb.data.to} not found as a contact in PiMonitor")
        val business = businessDao.load(rb.data.business.uid).await()
        val senderSpace = spacesDao.load(business.owningSpaceId).await()

        val inviteParams = Invite(
            invitorUserId = rb.session.user.uid,
            invitorSpaceId = rb.session.space.uid,
            invitedBusinessId = business.uid,
            invitedContactUserId = userContact.userId,
            status = listOf(
                InviteStatus.Sent(params = rb.data)
            )
        )
        val invite = invitesDao.create(inviteParams).await()
        val draft = EmailDraft(
            subject = rb.data.subject,
            body = "${rb.data.message}\n\nGoto https://react-client.vercel.app/connect?inviteId=${invite.uid}"
        )
        config.mailer.send(
            draft = draft,
            from = AddressInfo(
                name = senderSpace.name,
                email = "support@picortex.com"
            ),
            to = AddressInfo(rb.data.to)
        ).await()
        invite
    }
}