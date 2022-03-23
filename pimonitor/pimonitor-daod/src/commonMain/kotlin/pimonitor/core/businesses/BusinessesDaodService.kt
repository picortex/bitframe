package pimonitor.core.businesses

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.reports.utils.CategoryEntry
import akkounts.sage.SageOneZAService
import akkounts.sage.SageOneZAUserCompany
import bitframe.core.*
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import kash.Currency
import kash.Money
import kotlinx.collections.interoperable.*
import kotlinx.datetime.*
import later.Later
import later.await
import later.later
import pimonitor.core.accounting.FINANCIAL_REPORTS
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.businesses.params.toRegisterUserParams
import pimonitor.core.businesses.params.toValidatedCreateBusinessParams
import pimonitor.core.businesses.results.AvailableReportsResults
import pimonitor.core.contacts.ContactPersonBusinessInfo
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.invites.InfoResults
import pimonitor.core.invites.Invite
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.picortex.PiCortexDashboardProvider
import pimonitor.core.picortex.PiCortexDashboardProviderConfig
import pimonitor.core.picortex.PiCortexDashboardProviderConfig.Environment.Staging
import pimonitor.core.sage.SageApiCredentials
import pimonitor.core.spaces.SPACE_TYPE
import pimonitor.core.users.USER_TYPE
import presenters.changes.ChangeFeeling
import presenters.changes.moneyChangeBoxOf

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
    private val logger by config.logger()

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

    override fun operationalDashboard(rb: RequestBody.Authorized<String>) = config.scope.later {
        val business = load(rb).await()
        when (business.operationalBoard) {
            DASHBOARD_OPERATIONAL.NONE -> {
                InfoResults.NotShared("${business.name} has not shared their reports with any dashboard") as InfoResults<OperationalDashboard>
            }
            DASHBOARD_OPERATIONAL.PICORTEX -> {
                val cred = piCortexCredentialsDao.all(condition = PiCortexApiCredentials::businessId isEqualTo business.uid).await().last()
                InfoResults.Shared(piCortexDashboardProvider.technicalDashboardOf(cred).await())
            }
            else -> error("Business is connected to an unknown operational board provider")
        }
    }

    private fun SageApiCredentials.toCompany(business: MonitoredBusinessBasicInfo) = SageOneZAUserCompany(
        uid = business.uid,
        name = business.name,
        username = username,
        password = password,
        companyId = companyId
    )

    override fun load(rb: RequestBody.Authorized<String>): Later<MonitoredBusinessBasicInfo> = config.scope.later {
        monitoredBusinessesDao.load(uid = rb.data).await()
    }

    override fun availableReports(rb: RequestBody.Authorized<String>) = config.scope.later {
        val business = load(rb).await()
        val reports = when (business.financialBoard) {
            DASHBOARD_FINANCIAL.NONE -> emptyList()
            DASHBOARD_FINANCIAL.SAGE_ONE -> listOf(
                FINANCIAL_REPORTS.BALANCE_SHEET,
                FINANCIAL_REPORTS.INCOME_STATEMENT
            )
            else -> error("Business is connected to an unknown operational board provider")
        }
        AvailableReportsResults(business, reports)
    }

    override fun balanceSheet(rb: RequestBody.Authorized<String>) = config.scope.later {
        val business = load(rb).await()
        when (business.financialBoard) {
            DASHBOARD_FINANCIAL.NONE -> {
                InfoResults.NotShared("${business.name} has not shared their reports with any accounting system") as InfoResults<BalanceSheet>
            }
            DASHBOARD_FINANCIAL.SAGE_ONE -> {
                val cred = sageCredentialsDao.all(condition = SageApiCredentials::businessId isEqualTo business.uid).await().first()
                val company = cred.toCompany(business)
                val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
                InfoResults.Shared(sage.offeredTo(company).reports.balanceSheet(at = today).await())
            }
            else -> error("Business is connected to an unknown accounting provider")
        }
    }

    override fun incomeStatement(rb: RequestBody.Authorized<String>) = config.scope.later {
        val business = load(rb).await()

        when (business.financialBoard) {
            DASHBOARD_FINANCIAL.NONE -> {
                InfoResults.NotShared("${business.name} has not shared their reports with any accounting system") as InfoResults<IncomeStatement>
            }
            DASHBOARD_FINANCIAL.SAGE_ONE -> {
                val cred = sageCredentialsDao.all(
                    condition = SageApiCredentials::businessId isEqualTo business.uid
                ).await().last()
                val company = cred.toCompany(business)
                val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
                val lastMonth = today - DatePeriod(months = 1)
                InfoResults.Shared(sage.offeredTo(company).reports.incomeStatement(start = lastMonth, end = today).await())
            }
            else -> error("Business is connected to an unknown accounting provider")
        }
    }

    override fun all(rb: RequestBody.Authorized<BusinessFilter>) = config.scope.later {
        val condition = MonitoredBusinessBasicInfo::owningSpaceId isEqualTo rb.session.space.uid
        monitoredBusinessesDao.all(condition).await().filter { !it.deleted }.toTypedArray().map {
            summaryOf(it)
        }.toInteroperableList()
    }

    override fun delete(rb: RequestBody.Authorized<Array<out String>>) = config.scope.later {
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
                    revenue = moneyChangeBoxOf(
                        previous = earlyIncomeStatement.body.income.toMoney(currency),
                        current = laterIncomeStatement.body.income.toMoney(currency),
                        increaseFeeling = ChangeFeeling.Good,
                        decreaseFeeling = ChangeFeeling.Bad,
                        fixedFeeling = ChangeFeeling.Neutral
                    ),
                    expenses = moneyChangeBoxOf(
                        previous = earlyIncomeStatement.body.expenses.toMoney(currency),
                        current = laterIncomeStatement.body.expenses.toMoney(currency),
                        increaseFeeling = ChangeFeeling.Bad,
                        decreaseFeeling = ChangeFeeling.Good,
                        fixedFeeling = ChangeFeeling.Neutral
                    ),
                    grossProfit = moneyChangeBoxOf(
                        previous = Money.of(earlyIncomeStatement.body.grossProfit / currency.lowestDenomination, currency),
                        current = Money.of(laterIncomeStatement.body.grossProfit / currency.lowestDenomination, currency),
                        increaseFeeling = ChangeFeeling.Good,
                        decreaseFeeling = ChangeFeeling.Bad,
                        fixedFeeling = ChangeFeeling.Neutral
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