@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package pimonitor.api

import bitframe.client.BitframeApi
import pimonitor.client.authentication.signup.SignUpService
import kotlin.js.JsExport

interface PiMonitorApi : BitframeApi {
    val signUp: SignUpService

    //    val monitors: MonitorsService
//    val businesses: BusinessesService
//    val portfolio: PortfolioService

//    val monitorSession get() = monitors.session
}