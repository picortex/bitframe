package pimonitor.server

import bitframe.server.BitframeService
import bitframe.server.ServiceConfig
import pimonitor.core.business.financials.BusinessFinancialsServiceDaod
import pimonitor.core.business.interventions.BusinessInterventionsServiceDaod
import pimonitor.core.investments.BusinessInvestmentsServiceDaod
import pimonitor.core.business.operations.BusinessOperationsServiceDaod
import pimonitor.core.business.overview.BusinessOverviewServiceDaod
import pimonitor.core.businesses.BusinessesServiceDaod
import pimonitor.core.contacts.ContactsServiceDaod
import pimonitor.core.investments.InvestmentsServiceDaod
import pimonitor.core.invites.InvitesServiceDaod
import pimonitor.core.portfolio.PortfolioServiceDaod
import pimonitor.core.search.SearchServiceDaod
import pimonitor.core.signup.SignUpServiceDaod

class PiMonitorService(config: ServiceConfig) : BitframeService(config) {
    val signup by lazy { SignUpServiceDaod(config) }
    val businesses by lazy { BusinessesServiceDaod(config) }
    val businessOperations by lazy { BusinessOperationsServiceDaod(config) }
    val businessFinancials by lazy { BusinessFinancialsServiceDaod(config) }
    val businessInvestments by lazy { BusinessInvestmentsServiceDaod(config) }
    val businessInterventions by lazy { BusinessInterventionsServiceDaod(config) }
    val businessOverview by lazy { BusinessOverviewServiceDaod(config) }
    val contacts by lazy { ContactsServiceDaod(config) }
    val investments by lazy { InvestmentsServiceDaod(config) }
    val portfolio by lazy { PortfolioServiceDaod(config) }
    val search by lazy { SearchServiceDaod(config) }
    val invites by lazy { InvitesServiceDaod(config) }
}