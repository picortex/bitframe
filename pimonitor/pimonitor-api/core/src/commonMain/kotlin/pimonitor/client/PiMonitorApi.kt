@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeApi
import pimonitor.client.business.financials.BusinessFinancialsService
import pimonitor.client.business.operations.BusinessOperationsService
import pimonitor.client.businesses.BusinessesService
import pimonitor.client.contacts.ContactsService
import pimonitor.client.invites.InvitesService
import pimonitor.client.picortex.PiCortexDashboardService
import pimonitor.client.sage.SageDashboardService
import pimonitor.client.portfolio.PortfolioService
import pimonitor.client.search.SearchService
import pimonitor.client.signup.SignUpService
import pimonitor.core.picortex.PiCortexDashboardServiceCore
import kotlin.js.JsExport

@JsExport
interface PiMonitorApi : BitframeApi {
    val signUp: SignUpService
    val businesses: BusinessesService
    val businessOperations: BusinessOperationsService
    val businessFinancials: BusinessFinancialsService
    val contacts: ContactsService
    val portfolio: PortfolioService
    val search: SearchService
    val invites: InvitesService
}