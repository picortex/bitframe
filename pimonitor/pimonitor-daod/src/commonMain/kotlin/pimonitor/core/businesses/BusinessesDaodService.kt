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
import kotlinx.collections.interoperable.mutableListOf
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.*
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.*
import pimonitor.core.contacts.ContactPersonBusinessInfo
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.invites.Invite
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.picortex.PiCortexDashboardProvider
import pimonitor.core.picortex.PiCortexDashboardProviderConfig
import pimonitor.core.picortex.PiCortexDashboardProviderConfig.Environment.Staging
import pimonitor.core.sage.SageApiCredentials
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE
import presenters.containers.ChangeBox
import presenters.containers.ChangeRemark

open class BusinessesDaodService(
    open val config: ServiceConfigDaod
) : BusinessesServiceCore, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {

    private val factory get() = config.daoFactory
    private val monitorBusinessesDao by lazy { factory.get<MonitorBusinessBasicInfo>() }
    private val monitoredBusinessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val spacesDao by lazy { factory.get<Space>() }
    private val invitesDao by lazy { factory.get<Invite>() }
    private val userSpaceInfoDao by lazy { factory.get<UserSpaceInfo>() }
    private val userContactsDao by lazy { CompoundDao(factory.get<UserEmail>(), factory.get<UserPhone>()) }
    private val contactPersonBusinessInfoDao by lazy { factory.get<ContactPersonBusinessInfo>() }
    private val piCortexCredentialsDao by lazy { factory.get<PiCortexApiCredentials>() }
    private val sageCredentialsDao by lazy { factory.get<SageApiCredentials>() }
    private val piCortexDashboardProvider by lazy {
        val cfg = PiCortexDashboardProviderConfig(
            json = config.json, scope = config.scope, environment = Staging
        )
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

        val userId = if (registered == null) {
            val res1 = register(params.toRegisterUserParams()).await()
            res1.user.uid
        } else {
            val space = spacesDao.create(Space(name = params.businessName, type = SPACE_TYPE.MONITORED)).await()
            val usi = UserSpaceInfo(
                userId = registered.userId, spaceId = space.uid, type = USER_TYPE.CONTACT
            )
            userSpaceInfoDao.create(usi).await()
            registered.userId
        }

        val business = monitoredBusinessesDao.create(
            MonitoredBusinessBasicInfo(
                name = params.businessName, owningSpaceId = rb.session.space.uid
            )
        ).await()
        val cpbi = contactPersonBusinessInfoDao.create(
            ContactPersonBusinessInfo(
                userId = userId, businessId = business.uid, position = ""
            )
        ).await()
        CreateMonitoredBusinessResult(
            params = params, business = business, contact = cpbi, summary = summaryOf(business)
        )
    }

    override fun operationalDashboard(rb: RequestBody.Authorized<String>): Later<OperationalDashboard?> = config.scope.later {
        val businessId = rb.data

        val business = monitoredBusinessesDao.loadOrNull(businessId).await()?.takeIf {
            it.operationalBoard == DASHBOARD_OPERATIONAL.PICORTEX
        } ?: return@later null
        
        val cred = piCortexCredentialsDao.all(condition = PiCortexApiCredentials::businessId isEqualTo business.uid).await().first()
        piCortexDashboardProvider.technicalDashboardOf(cred).await()
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo rb.session.space.uid
        monitoredBusinessesDao.all(condition).await().filter { !it.deleted }.toTypedArray().map {
            summaryOf(it)
        }.toInteroperableList()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<MonitoredBusinessBasicInfo>> = config.scope.later {
        val list = mutableListOf<MonitoredBusinessBasicInfo>()
        for (business in rb.data) list.add(monitoredBusinessesDao.delete(business).await())
        list
    }

    private fun CategoryEntry.toMoney(currency: Currency) = Money.of(total.toDouble() / currency.lowestDenomination, currency)

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
                uid = business.uid, name = business.name, username = cred.username, password = cred.password, companyId = cred.companyId
            )
            val ap = sage.offeredTo(company)
            try {
                val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                val earlyIncomeStatement = ap.reports.incomeStatement(
                    start = now.date - DatePeriod(months = 2), end = now.date - DatePeriod(months = 1)
                ).await()
                val laterIncomeStatement = ap.reports.incomeStatement(
                    start = now.date - DatePeriod(months = 1), end = now.date
                ).await()
                val currency = earlyIncomeStatement.header.currency
                bus.copy(
                    revenue = ChangeBox(
                        previous = earlyIncomeStatement.body.income.toMoney(currency),
                        current = laterIncomeStatement.body.income.toMoney(currency),
                        details = "Updated now",
                        change = ChangeRemark.CONSTANT
                    ), expenses = ChangeBox(
                        previous = earlyIncomeStatement.body.expenses.toMoney(currency),
                        current = laterIncomeStatement.body.expenses.toMoney(currency),
                        details = "Updated now",
                        change = ChangeRemark.CONSTANT
                    ), grossProfit = ChangeBox(
                        previous = Money.of(earlyIncomeStatement.body.grossProfit / currency.lowestDenomination, currency),
                        current = Money.of(laterIncomeStatement.body.grossProfit / currency.lowestDenomination, currency),
                        details = "Updated now",
                        change = ChangeRemark.CONSTANT
                    )
                )
            } catch (exp: Throwable) {
                bus
            }
        } else {
            bus
        }
    }
}