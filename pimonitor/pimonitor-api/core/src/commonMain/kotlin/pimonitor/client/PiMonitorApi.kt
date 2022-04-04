@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeApi
import bitframe.client.BitframeEvents
import pimonitor.client.business.investments.BusinessInvestmentsService
import pimonitor.client.business.financials.BusinessFinancialsService
import pimonitor.client.business.interventions.BusinessInterventionService
import pimonitor.client.business.operations.BusinessOperationsService
import pimonitor.client.businesses.BusinessesService
import pimonitor.client.contacts.ContactsService
import pimonitor.client.events.PiMonitorEvents
import pimonitor.client.invites.InvitesService
import pimonitor.client.portfolio.PortfolioService
import pimonitor.client.search.SearchService
import pimonitor.client.signup.SignUpService
import kotlin.js.JsExport

@JsExport
interface PiMonitorApi : BitframeApi {
    override val events: PiMonitorEvents
    val signUp: SignUpService
    val businesses: BusinessesService
    val businessInvestments: BusinessInvestmentsService
    val businessInterventions: BusinessInterventionService
    val businessOperations: BusinessOperationsService
    val businessFinancials: BusinessFinancialsService
    val contacts: ContactsService
    val portfolio: PortfolioService
    val search: SearchService
    val invites: InvitesService
}