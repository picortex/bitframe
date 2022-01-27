package pimonitor.portfolio

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import later.await
import later.later
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.MonitoredBusiness
import presenters.cards.ValueCard
import presenters.fields.BooleanInputField
import kotlin.random.Random

class PortfolioService(
    private val businessesService: BusinessesService
) {
    private val config get() = businessesService.config
    private val scope get() = config.scope

    fun getPortfolioData() = scope.later {
        val businesses = businessesService.all().await()
        val data = PortfolioData(
            cards = listOf(
                totalBusinessesCard(businesses),
                contactsCard(businesses),
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
            BooleanInputField("Setup Account", true),
            BooleanInputField("Complete Your Space", false),
            BooleanInputField("Invite Team Members", false),
            BooleanInputField("Add Payment Method", false),
            BooleanInputField("Add Integrations", false)
        )
    )

    private fun totalBusinessesCard(businesses: List<MonitoredBusiness>) = ValueCard(
        title = "Total businesses",
        value = businesses.size.toString(),
        details = "Last updated now"
    )

    private fun contactsCard(businesses: List<MonitoredBusiness>) = ValueCard(
        title = "Contacts",
        value = businesses.flatMap { it.contacts }.size.toString(),
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