package pimonitor.client

import bitframe.client.BitframeApiMock
import bitframe.client.BitframeApiMockConfig
import pimonitor.client.business.financials.BusinessFinancialsServiceMock
import pimonitor.client.business.operations.BusinessOperationsServiceMock
import pimonitor.client.business.overview.BusinessOverviewServiceMock
import pimonitor.client.businesses.BusinessesServiceMock
import pimonitor.client.contacts.ContactsServiceMock
import pimonitor.client.events.PiMonitorEvents
import pimonitor.client.interventions.InterventionsServiceMock
import pimonitor.client.investments.InvestmentsServiceMock
import pimonitor.client.invites.InvitesServiceMock
import pimonitor.client.portfolio.PortfolioServiceMock
import pimonitor.client.search.SearchServiceMock
import pimonitor.client.signup.SignUpServiceMock
import kotlin.jvm.JvmOverloads

class PiMonitorApiMock @JvmOverloads constructor(
    override val config: BitframeApiMockConfig = BitframeApiMockConfig()
) : PiMonitorApi, BitframeApiMock by BitframeApiMock(config) {
    override val businesses by lazy { BusinessesServiceMock(config) }
    override val businessFinancials by lazy { BusinessFinancialsServiceMock(config) }
    override val businessOperations by lazy { BusinessOperationsServiceMock(config) }
    override val businessOverview by lazy { BusinessOverviewServiceMock(config) }
    override val contacts by lazy { ContactsServiceMock(config) }
    override val events by lazy { PiMonitorEvents(config.bus) }
    override val invites by lazy { InvitesServiceMock(config) }
    override val interventions by lazy { InterventionsServiceMock(config) }
    override val investments by lazy { InvestmentsServiceMock(config) }
    override val portfolio by lazy { PortfolioServiceMock(investments, interventions) }
    override val search by lazy { SearchServiceMock(config) }
    override val signUp by lazy { SignUpServiceMock(config) }
}