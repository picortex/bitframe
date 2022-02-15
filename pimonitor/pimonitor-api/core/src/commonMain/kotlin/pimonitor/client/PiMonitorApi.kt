package pimonitor.client

import bitframe.client.BitframeApi
import pimonitor.client.register.RegisterService
import pimonitor.client.signup.SignUpService
import kotlin.js.JsExport

@JsExport
interface PiMonitorApi : BitframeApi {
    val signUp: SignUpService

    val register get() = RegisterService(signUp, signIn)

    //    val monitors: MonitorsService
//    val businesses: BusinessesService
//    val portfolio: PortfolioService

//    val monitorSession get() = monitors.session
}