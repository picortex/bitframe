@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeApi
import pimonitor.client.business.financials.BusinessFinancialsService
import pimonitor.client.business.operations.BusinessOperationsService
import pimonitor.client.business.overview.BusinessOverviewService
import pimonitor.client.businesses.BusinessesService
import pimonitor.client.contacts.ContactsService
import pimonitor.client.events.PiMonitorEvents
import pimonitor.client.interventions.InterventionService
import pimonitor.client.investments.InvestmentsService
import pimonitor.client.invites.InvitesService
import pimonitor.client.portfolio.PortfolioService
import pimonitor.client.search.SearchService
import pimonitor.client.signup.SignUpService
import kotlin.js.JsExport

@JsExport
interface PiMonitorApi : BitframeApi {
    val businesses: BusinessesService
    val businessFinancials: BusinessFinancialsService
    val businessOperations: BusinessOperationsService
    val businessOverview: BusinessOverviewService
    val contacts: ContactsService
    override val events: PiMonitorEvents
    val interventions: InterventionService
    val investments: InvestmentsService
    val invites: InvitesService
    val portfolio: PortfolioService
    val search: SearchService
    val signUp: SignUpService
}