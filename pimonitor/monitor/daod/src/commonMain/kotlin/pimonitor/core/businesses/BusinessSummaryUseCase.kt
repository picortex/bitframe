package pimonitor.core.businesses

import akkounts.sage.SageOneZAUserCompany
import bitframe.core.*
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.*
import later.await
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.configs.MonitorServiceConfigDaod
import pimonitor.core.contacts.ContactPersonBusinessInfo
import pimonitor.core.invites.Invite
import pimonitor.core.sage.SageApiCredentials
import presenters.changes.ChangeFeeling
import presenters.changes.moneyChangeBoxOf

internal class BusinessSummaryUseCase(
    private val config: MonitorServiceConfigDaod
) {
    private val factory get() = config.daoFactory

    private val invitesDao by lazy { factory.get<Invite>() }
    private val userContactsDao by lazy { CompoundDao(factory.get<UserEmail>(), factory.get<UserPhone>()) }
    private val contactPersonBusinessInfoDao by lazy { factory.get<ContactPersonBusinessInfo>() }
    private val sageCredentialsDao by lazy { factory.get<SageApiCredentials>() }
    private val sage by lazy { config.sage }

    suspend operator fun invoke(business: MonitoredBusinessBasicInfo): MonitoredBusinessSummary {
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
                val earlyIncomeStatementLater = ap.reports.incomeStatement(
                    start = now.date - DatePeriod(months = 2), end = now.date - DatePeriod(months = 1)
                )
                val laterIncomeStatementLater = ap.reports.incomeStatement(
                    start = now.date - DatePeriod(months = 1), end = now.date
                )
                val earlyIncomeStatement = earlyIncomeStatementLater.await()
                val laterIncomeStatement = laterIncomeStatementLater.await()
                bus.copy(
                    revenue = moneyChangeBoxOf(
                        title = "Revenue",
                        previous = earlyIncomeStatement.body.revenue.total,
                        current = laterIncomeStatement.body.revenue.total,
                        increaseFeeling = ChangeFeeling.Good,
                        decreaseFeeling = ChangeFeeling.Bad,
                        fixedFeeling = ChangeFeeling.Neutral
                    ),
                    expenses = moneyChangeBoxOf(
                        title = "Expenses",
                        previous = earlyIncomeStatement.body.expenses.total,
                        current = laterIncomeStatement.body.expenses.total,
                        increaseFeeling = ChangeFeeling.Bad,
                        decreaseFeeling = ChangeFeeling.Good,
                        fixedFeeling = ChangeFeeling.Neutral
                    ),
                    grossProfit = moneyChangeBoxOf(
                        title = "Gross Profit",
                        previous = earlyIncomeStatement.body.grossProfit,
                        current = laterIncomeStatement.body.grossProfit,
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