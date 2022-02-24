package pimonitor.core.portfolio

import bitframe.core.DaodServiceConfig
import bitframe.core.RequestBody
import bitframe.core.get
import bitframe.core.isEqualTo
import kotlinx.collections.interoperable.listOf
import later.await
import later.later
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.contacts.ContactPersonSpaceInfo
import presenters.cards.ValueCard
import presenters.fields.BooleanInputField
import kotlin.random.Random

open class PortfolioDaodService(
    override val config: DaodServiceConfig
) : PortfolioServiceCore {

    val businessBasicInfoDao by lazy { config.daoFactory.get<MonitoredBusinessBasicInfo>() }
    val contactPersonSpaceInfoDao by lazy { config.daoFactory.get<ContactPersonSpaceInfo>() }

    override fun load(rb: RequestBody.Authorized<PortfolioFilter>) = config.scope.later {
        val businesses = businessBasicInfoDao.all(
            MonitoredBusinessBasicInfo::owningSpaceId isEqualTo rb.session.space.uid
        ).await()

        val contacts = businesses.flatMap {
            contactPersonSpaceInfoDao.all(ContactPersonSpaceInfo::spaceId isEqualTo it.spaceId).await()
        }

        PortfolioData(
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
            )
        )
    }
}