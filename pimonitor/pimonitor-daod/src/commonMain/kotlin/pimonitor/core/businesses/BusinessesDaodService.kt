package pimonitor.core.businesses

import akkounts.reports.utils.CategoryEntry
import akkounts.sage.SageOneZAService
import akkounts.sage.SageOneZAUserCompany
import bitframe.core.*
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import kash.Currency
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.mutableListOf
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.*
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
import pimonitor.core.sage.SageApiCredentials
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE
import presenters.containers.ChangeBox
import presenters.containers.ChangeRemark
import kotlin.time.Duration.Companion.days

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
    private val sageCredentialsDao by lazy { factory.get<SageApiCredentials>() }
    private val piCortexDashboardProvider by lazy {
        val cfg = PiCortexDashboardProviderConfig(json = config.json, scope = config.scope)
        PiCortexDashboardProvider(cfg)
    }
    private val sage by lazy { SageOneZAService("{C7542EBF-4657-484C-B79E-E3D90DB0F0D1}") }
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

    private fun CategoryEntry.toMoney(currency: Currency) = Money.of(total * currency.lowestDenomination, currency)

    private suspend fun summaryOf(business: MonitoredBusinessBasicInfo): MonitoredBusinessSummary {
        val contacts = contactPersonBusinessInfoDao.all(ContactPersonBusinessInfo::businessId isEqualTo business.uid).await().flatMap {
            userContactsDao.all(UserContact::userId isEqualTo it.userId).await()
        }.toInteroperableList()
        val invites = invitesDao.all(Invite::invitedBusinessId isEqualTo business.uid).await()
        val bus = MonitoredBusinessSummary(
            uid = business.uid,
            name = business.name,
            contacts = contacts,
            invites = invites,
            operationalBoard = business.operationalBoard,
            financialBoard = business.financialBoard,
            interventions = "0 of 0"
        )
        return if (business.financialBoard == DASHBOARD_FINANCIAL.SAGE_ONE) {
            val cred = sageCredentialsDao.all(SageApiCredentials::businessId isEqualTo business.uid).await().first()
            val company = SageOneZAUserCompany(
                uid = business.uid,
                name = business.name,
                username = cred.username,
                password = cred.password,
                companyId = cred.companyId
            )
            val ap = sage.offeredTo(company)
            try {
                val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                val earlyIncomeStatement = ap.reports.incomeStatement(
                    start = now.date - DatePeriod(months = 2),
                    end = now.date - DatePeriod(months = 1)
                ).await()
                val laterIncomeStatement = ap.reports.incomeStatement(
                    start = now.date - DatePeriod(months = 1),
                    end = now.date
                ).await()
                val currency = earlyIncomeStatement.header.currency
                bus.copy(
                    revenue = ChangeBox(
                        precursor = earlyIncomeStatement.body.income.toMoney(currency),
                        successor = laterIncomeStatement.body.income.toMoney(currency),
                        details = "Updated now",
                        remark = ChangeRemark.CONSTANT
                    ),
                    grossProfit = ChangeBox(
                        precursor = Money.of(earlyIncomeStatement.body.grossProfit*currency.lowestDenomination,currency),
                        successor = Money.of(laterIncomeStatement.body.grossProfit*currency.lowestDenomination,currency),
                        details = "Updated now",
                        remark = ChangeRemark.CONSTANT
                    )
                )
            } catch (exp: Throwable) {
                bus
            }
        } else {
            bus
        }
    }

    override fun invite(rb: RequestBody.Authorized<InviteToShareReportsParams>): Later<Invite> = config.scope.later {
        val userContact = userContactsDao.all(
            condition = UserContact::value isEqualTo rb.data.to
        ).await().firstOrNull() ?: error("Business contact with email ${rb.data.to} is not found in pimonitor")

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