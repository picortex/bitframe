package pimonitor.server

import bitframe.server.BitframeService
import pimonitor.core.business.financials.BusinessFinancialsServiceDaod
import pimonitor.core.business.operations.BusinessOperationsServiceDaod
import pimonitor.core.business.overview.BusinessOverviewServiceDaod
import pimonitor.core.businesses.BusinessesServiceDaod
import pimonitor.core.configs.MonitorServiceConfigDaod
import pimonitor.core.contacts.ContactsServiceDaod
import pimonitor.core.interventions.InterventionsServiceDaod
import pimonitor.core.investments.InvestmentsServiceDaod
import pimonitor.core.invites.InvitesServiceDaod
import pimonitor.core.portfolio.PortfolioServiceDaod
import pimonitor.core.search.SearchServiceDaod
import pimonitor.core.signup.SignUpServiceDaod

class MonitorService(config: MonitorServiceConfig) : BitframeService(config) {
    val signup by lazy { SignUpServiceDaod(config) }
    val businesses by lazy { BusinessesServiceDaod(config) }
    val businessOperations by lazy { BusinessOperationsServiceDaod(config) }
    val businessFinancials by lazy { BusinessFinancialsServiceDaod(config) }
    val businessOverview by lazy { BusinessOverviewServiceDaod(config) }
    val contacts by lazy { ContactsServiceDaod(config) }
    val investments by lazy { InvestmentsServiceDaod(config) }
    val interventions by lazy { InterventionsServiceDaod(config) }
    val portfolio by lazy { PortfolioServiceDaod(config, investments, interventions) }
    val search by lazy { SearchServiceDaod(config) }
    val invites by lazy { InvitesServiceDaod(config) }
}