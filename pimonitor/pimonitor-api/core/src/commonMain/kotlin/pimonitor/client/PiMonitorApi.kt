@file:JsExport

package pimonitor.client

import bitframe.client.BitframeApi
import pimonitor.client.signup.SignUpService
import kotlin.js.JsExport

interface PiMonitorApi : BitframeApi {
    val signUp: SignUpService

    //    val monitors: MonitorsService
//    val businesses: BusinessesService
//    val portfolio: PortfolioService

//    val monitorSession get() = monitors.session
}