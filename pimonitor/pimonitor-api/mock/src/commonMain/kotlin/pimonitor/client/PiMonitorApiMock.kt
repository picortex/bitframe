package pimonitor.client

import bitframe.client.BitframeApiMock
import bitframe.client.BitframeApiMockConfig
import pimonitor.client.business.financials.BusinessFinancialsServiceMock
import pimonitor.client.business.interventions.BusinessInterventionService
import pimonitor.client.business.interventions.BusinessInterventionsServiceMock
import pimonitor.client.business.investments.BusinessInvestmentsServiceMock
import pimonitor.client.business.operations.BusinessOperationsServiceMock
import pimonitor.client.businesses.BusinessesServiceMock
import pimonitor.client.contacts.ContactsServiceMock
import pimonitor.client.invites.InvitesServiceMock
import pimonitor.client.portfolio.PortfolioServiceMock
import pimonitor.client.search.SearchServiceMock
import pimonitor.client.signup.SignUpServiceMock
import kotlin.jvm.JvmOverloads

class PiMonitorApiMock @JvmOverloads constructor(
    override val config: BitframeApiMockConfig = BitframeApiMockConfig()
) : PiMonitorApi, BitframeApiMock by BitframeApiMock(config) {
    override val signUp by lazy { SignUpServiceMock(config) }
    override val businesses by lazy { BusinessesServiceMock(config) }
    override val businessOperations by lazy { BusinessOperationsServiceMock(config) }
    override val businessFinancials by lazy { BusinessFinancialsServiceMock(config) }
    override val businessInvestments by lazy { BusinessInvestmentsServiceMock(config) }
    override val businessInterventions by lazy { BusinessInterventionsServiceMock(config) }
    override val contacts by lazy { ContactsServiceMock(config) }
    override val portfolio by lazy { PortfolioServiceMock(config) }
    override val search by lazy { SearchServiceMock(config) }
    override val invites by lazy { InvitesServiceMock(config) }
}