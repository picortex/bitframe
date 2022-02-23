@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeApi
import pimonitor.client.businesses.BusinessesService
import pimonitor.client.contacts.ContactsService
import pimonitor.client.signup.SignUpService
import pimonitor.core.portfolio.PortfolioService
import kotlin.js.JsExport

@JsExport
interface PiMonitorApi : BitframeApi {
    val signUp: SignUpService
    val businesses: BusinessesService
    val contacts: ContactsService
    val portfolio: PortfolioService
}