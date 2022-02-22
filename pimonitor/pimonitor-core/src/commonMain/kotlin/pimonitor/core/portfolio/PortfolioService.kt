package pimonitor.core.portfolio

import bitframe.core.ServiceConfig
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import later.later
import pimonitor.core.businesses.BusinessesServiceCore
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.cards.ValueCard
import presenters.fields.BooleanInputField
import kotlin.random.Random

class PortfolioService(
    val config: ServiceConfig
) {
    private val scope get() = config.scope

    fun getPortfolioData() = scope.later {
//        val businesses = businessesService.all().await()
        val data = PortfolioData(
            cards = listOf(
                totalBusinessesCard(listOf()),//totalBusinessesCard(businesses),
                contactsCard(listOf()),//contactsCard(businesses),
                team(),
                employees(),
                aggregatedRevenue()
            ),
            profileProgress = progress()
        )
        data
    }

    private fun progress() = ProfileProgress(
        title = "Complete your profile",
        items = listOf(
            BooleanInputField("Setup Account", value = true),
            BooleanInputField("Complete Your Space", value = false),
            BooleanInputField("Invite Team Members", value = false),
            BooleanInputField("Add Payment Method", value = false),
            BooleanInputField("Add Integrations", value = false)
        )
    )

    private fun totalBusinessesCard(businesses: List<MonitoredBusinessBasicInfo>) = ValueCard(
        title = "Total businesses",
        value = businesses.size.toString(),
        details = "Last updated now"
    )

    private fun contactsCard(businesses: List<MonitoredBusinessBasicInfo>) = ValueCard(
        title = "Contacts",
        value = "12", //businesses.flatMap { it.contacts }.size.toString(),
        details = "Last updated now"
    )

    private fun team() = ValueCard(
        title = "Team",
        value = Random.nextInt(0, 10).toString(),
        details = "Last updated now"
    )

    private fun employees() = ValueCard(
        title = "Total Employees",
        value = Random.nextInt(2, 50).toString(),
        details = "Last updated now"
    )

    private fun aggregatedRevenue() = ValueCard(
        title = "Aggregated Revenue",
        value = (Random.nextInt(300, 900) * 1000).toString(),
        details = "Last updated now"
    )
}