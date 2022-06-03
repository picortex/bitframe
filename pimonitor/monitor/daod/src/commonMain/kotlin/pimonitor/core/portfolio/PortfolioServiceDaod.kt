package pimonitor.core.portfolio

import bitframe.core.*
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later
import pimonitor.core.businesses.BusinessSummaryUseCase
import pimonitor.core.businesses.BusinessesServiceCore
import pimonitor.core.businesses.BusinessesServiceDaod
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.configs.MonitorServiceConfigDaod
import pimonitor.core.contacts.ContactPersonBusinessInfo
import pimonitor.core.interventions.Intervention
import pimonitor.core.interventions.InterventionsServiceCore
import pimonitor.core.interventions.InterventionsServiceDaod
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentsServiceCore
import pimonitor.core.investments.InvestmentsServiceDaod
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import presenters.cards.ValueCard
import presenters.fields.BooleanInputField

open class PortfolioServiceDaod(
    val config: MonitorServiceConfigDaod,
    val investments: InvestmentsServiceCore,
    val interventions: InterventionsServiceCore
) : PortfolioServiceCore {

    private val factory get() = config.daoFactory
    private val businessBasicInfoDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val contactPersonSpaceInfoDao by lazy { factory.get<ContactPersonBusinessInfo>() }
    private val spacesDao by lazy { factory.get<Space>() }
    private val summary by lazy { BusinessSummaryUseCase(config) }

    override fun load(rb: RequestBody.Authorized<PortfolioFilter>) = config.scope.later {
        val space = spacesDao.load(rb.session.space.uid).await()
        val bizs = businessBasicInfoDao.all(
            MonitoredBusinessBasicInfo::owningSpaceId isEqualTo rb.session.space.uid
        ).await()

        val contacts = bizs.flatMap {
            contactPersonSpaceInfoDao.all(ContactPersonBusinessInfo::businessId isEqualTo it.uid).await()
        }
        val params = rb.map { DisbursableFilter(businessId = null) }
        MonitorPortfolio(
            space = space,
            cards = listOf(
                ValueCard(
                    title = "Total businesses",
                    value = bizs.size.toString(),
                    details = "Last updated now"
                ),
                ValueCard(
                    title = "Contacts",
                    value = contacts.size.toString(),
                    details = "Last updated now"
                ),
                ValueCard(
                    title = "Team",
                    value = "0",
                    details = "Last updated now"
                ),
                ValueCard(
                    title = "Total Employees",
                    value = "0",
                    details = "Last updated now"
                ),
                ValueCard(
                    title = "Aggregated Revenue",
                    value = "0",
                    details = "Last updated now"
                )
            ),
            profileProgress = ProfileProgress(
                title = "Complete your profile",
                items = listOf(
                    BooleanInputField("Setup Account", value = true),
                    BooleanInputField("Complete Your Space", value = false),
                    BooleanInputField("Invite Team Members", value = false),
                    BooleanInputField("Add Payment Method", value = false),
                    BooleanInputField("Add Integrations", value = false)
                )
            ),
            businesses = bizs.take(5).map { summary(it) }.toInteroperableList(),
            interventions = interventions.all(params).await().take(5).toInteroperableList(),
            investments = investments.all(params).await().take(5).toInteroperableList()
        )
    }
}