package pimonitor.client

import bitframe.client.BitframeApi
import bitframe.client.BitframeApiKtor
import bitframe.client.BitframeApiKtorConfig
import pimonitor.client.business.financials.BusinessFinancialsServiceKtor
import pimonitor.client.business.operations.BusinessOperationsServiceKtor
import pimonitor.client.business.overview.BusinessOverviewServiceKtor
import pimonitor.client.businesses.BusinessesServiceKtor
import pimonitor.client.contacts.ContactsServiceKtor
import pimonitor.client.events.PiMonitorEvents
import pimonitor.client.interventions.InterventionsServiceKtor
import pimonitor.client.investments.InvestmentsServiceKtor
import pimonitor.client.invites.InvitesServiceKtor
import pimonitor.client.portfolio.PortfolioServiceKtor
import pimonitor.client.search.SearchServiceKtor
import pimonitor.client.signup.SignUpServiceKtor

class MonitorApiKtor(
    override val config: BitframeApiKtorConfig,
) : MonitorApi, BitframeApi by BitframeApiKtor(config) {
    override val businesses by lazy { BusinessesServiceKtor(config) }
    override val businessFinancials by lazy { BusinessFinancialsServiceKtor(config) }
    override val businessOverview by lazy { BusinessOverviewServiceKtor(config) }
    override val businessOperations by lazy { BusinessOperationsServiceKtor(config) }
    override val contacts by lazy { ContactsServiceKtor(config) }
    override val events by lazy { PiMonitorEvents(config.bus) }
    override val invites by lazy { InvitesServiceKtor(config) }
    override val interventions by lazy { InterventionsServiceKtor(config) }
    override val investments by lazy { InvestmentsServiceKtor(config) }
    override val portfolio by lazy { PortfolioServiceKtor(config) }
    override val search by lazy { SearchServiceKtor(config) }
    override val signUp by lazy { SignUpServiceKtor(config) }
    override fun toString(): String = "PiMonitorApiKtor(url=${config.url})"
}