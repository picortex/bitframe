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
import mailer.EmailDraft
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.businesses.params.toRegisterUserParams
import pimonitor.core.contacts.ContactPersonSpaceInfo
import pimonitor.core.invites.Invite
import pimonitor.core.invites.InviteStatus
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE

open class BusinessesDaodService(
    open val config: DaodServiceConfig
) : BusinessesServiceCore, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {

    private val businessDao by lazy { config.daoFactory.get<MonitoredBusinessBasicInfo>() }
    private val spacesDao by lazy { config.daoFactory.get<Space>() }
    private val invitesDao by lazy { config.daoFactory.get<Invite>() }
    private val userSpaceInfoDao by lazy { config.daoFactory.get<UserSpaceInfo>() }
    private val userContactsDao by lazy {
        CompoundDao(
            config.daoFactory.get<UserEmail>(),
            config.daoFactory.get<UserPhone>()
        )
    }
    private val contactPersonSpaceInfoDao by lazy {
        config.daoFactory.get<ContactPersonSpaceInfo>()
    }

    private val logger
        get() = config.logger.with(
            "source" to this::class.simpleName
        )

    override fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>) = config.scope.later {
        val registered = userContactsDao.all(
            condition = UserContact::value isEqualTo rb.data.contactEmail
        ).await().firstOrNull()

        val (userId, spaceId) = if (registered == null) {
            val res1 = register(rb.data.toRegisterUserParams()).await()
            res1.user.uid to res1.spaces.first().uid
        } else {
            val space = spacesDao.create(Space(name = rb.data.businessName, type = SPACE_TYPE.MONITORED)).await()
            val usi = UserSpaceInfo(
                userId = registered.userId,
                spaceId = space.uid,
                type = USER_TYPE.CONTACT
            )
            userSpaceInfoDao.create(usi).await()
            registered.userId to space.uid
        }

        val task2_1 = businessDao.create(
            MonitoredBusinessBasicInfo(
                spaceId = spaceId,
                owningSpaceId = rb.session.space.uid
            )
        )
        val task2_2 = contactPersonSpaceInfoDao.create(
            ContactPersonSpaceInfo(
                userId = userId,
                spaceId = spaceId,
                owningSpaceId = rb.session.space.uid,
                position = ""
            )
        )
        CreateMonitoredBusinessResult(
            params = rb.data,
            business = task2_1.await(),
            contact = task2_2.await()
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
        val space = spacesDao.load(business.spaceId).await()
        val contacts = contactPersonSpaceInfoDao.all(ContactPersonSpaceInfo::spaceId isEqualTo space.uid).await().flatMap {
            userContactsDao.all(UserContact::userId isEqualTo it.userId).await()
        }.toInteroperableList()
        val invites = invitesDao.all(Invite::invitedBusinessId isEqualTo business.uid).await()
        return if (business.dashboard == DASHBOARD.NONE) {
            MonitoredBusinessSummary.UnConnectedDashboard(
                uid = business.uid,
                name = space.name,
                contacts = contacts,
                invites = invites,
                interventions = "0 of 0"
            )
        } else {
            TODO()
        }
    }

    override fun invite(rb: RequestBody.Authorized<InviteToShareReportsParams>): Later<Invite> = config.scope.later {
        val contact = userContactsDao.all(
            condition = UserContact::value isEqualTo rb.data.to
        ).await().firstOrNull() ?: error("Business contact with email ${rb.data.to} is not found in pimonitor")

        val contactSpaceInfo = contactPersonSpaceInfoDao.all(
            condition = ContactPersonSpaceInfo::owningSpaceId isEqualTo rb.session.space.uid
        ).await().firstOrNull {
            it.userId == contact.userId
        } ?: error("User with email ${rb.data.to} not found as a contact in PiMonitor")

        val business = businessDao.all(
            condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo rb.session.space.uid
        ).await().first {
            it.spaceId == contactSpaceInfo.spaceId
        }

        val invite = Invite(
            invitorUserId = rb.session.user.uid,
            invitorSpaceId = rb.session.space.uid,
            invitedBusinessId = business.uid,
            invitedBusinessSpaceId = business.spaceId,
            invitedContactUserId = contact.userId,
            status = listOf(
                InviteStatus.Sent(params = rb.data)
            )
        )

        val draft = EmailDraft(
            subject = rb.data.subject,
            body = rb.data.message
        )
        config.mailer.send(draft = draft, from = "support@picortex.com", to = rb.data.to).await()
        invitesDao.create(invite).await()
    }
}