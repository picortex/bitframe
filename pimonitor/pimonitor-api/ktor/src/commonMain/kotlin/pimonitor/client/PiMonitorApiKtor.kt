package pimonitor.client

import bitframe.client.BitframeApi
import bitframe.client.BitframeApiKtor
import bitframe.client.BitframeApiKtorConfig
import pimonitor.client.business.financials.BusinessFinancialsServiceKtor
import pimonitor.client.business.interventions.BusinessInterventionService
import pimonitor.client.business.interventions.BusinessInterventionsServiceKtor
import pimonitor.client.business.investments.BusinessInvestmentsServiceKtor
import pimonitor.client.business.operations.BusinessOperationsServiceKtor
import pimonitor.client.business.overview.BusinessOverviewService
import pimonitor.client.business.overview.BusinessOverviewServiceKtor
import pimonitor.client.businesses.BusinessesServiceKtor
import pimonitor.client.contacts.ContactsServiceKtor
import pimonitor.client.events.PiMonitorEvents
import pimonitor.client.investments.InvestmentsService
import pimonitor.client.investments.InvestmentsServiceKtor
import pimonitor.client.invites.InvitesServiceKtor
import pimonitor.client.portfolio.PortfolioServiceKtor
import pimonitor.client.search.SearchServiceKtor
import pimonitor.client.signup.SignUpServiceKtor

class PiMonitorApiKtor(
    override val config: BitframeApiKtorConfig,
) : PiMonitorApi, BitframeApi by BitframeApiKtor(config) {
    override val businesses by lazy { BusinessesServiceKtor(config) }
    override val businessFinancials by lazy { BusinessFinancialsServiceKtor(config) }
    override val businessInterventions by lazy { BusinessInterventionsServiceKtor(config) }
    override val businessInvestments by lazy { BusinessInvestmentsServiceKtor(config) }
    override val businessOverview by lazy { BusinessOverviewServiceKtor(config) }
    override val businessOperations by lazy { BusinessOperationsServiceKtor(config) }
    override val contacts by lazy { ContactsServiceKtor(config) }
    override val events by lazy { PiMonitorEvents(config.bus) }
    override val invites by lazy { InvitesServiceKtor(config) }
    override val investments by lazy { InvestmentsServiceKtor(config) }
    override val portfolio by lazy { PortfolioServiceKtor(config) }
    override val search by lazy { SearchServiceKtor(config) }
    override val signUp by lazy { SignUpServiceKtor(config) }
}