package pimonitor.core.portfolio

import bitframe.core.*
import kotlinx.collections.interoperable.listOf
import later.await
import later.later
import pimonitor.core.businesses.BusinessesServiceDaod
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.contacts.ContactPersonBusinessInfo
import pimonitor.core.interventions.Intervention
import pimonitor.core.investments.Investment
import presenters.cards.ValueCard
import presenters.fields.BooleanInputField

open class PortfolioServiceDaod(
    val businessesService: BusinessesServiceDaod,
) : PortfolioServiceCore {

    val config get() = businessesService.config
    val factory get() = config.daoFactory
    val businessBasicInfoDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    val interventionsDao by lazy { factory.get<Intervention>() }
    val investmentsDao by lazy { factory.get<Investment>() }
    val contactPersonSpaceInfoDao by lazy { factory.get<ContactPersonBusinessInfo>() }
    val spacesDao by lazy { factory.get<Space>() }

    override fun load(rb: RequestBody.Authorized<PortfolioFilter>) = config.scope.later {
        val space = spacesDao.load(rb.session.space.uid).await()
        val businesses = businessBasicInfoDao.all(
            MonitoredBusinessBasicInfo::owningSpaceId isEqualTo rb.session.space.uid
        ).await()

        val contacts = businesses.flatMap {
            contactPersonSpaceInfoDao.all(ContactPersonBusinessInfo::businessId isEqualTo it.uid).await()
        }

        MonitorPortfolio(
            space = space,
            cards = listOf(
                ValueCard(
                    title = "Total businesses",
                    value = businesses.size.toString(),
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
            businesses = businessesService.query()
        )
    }
}