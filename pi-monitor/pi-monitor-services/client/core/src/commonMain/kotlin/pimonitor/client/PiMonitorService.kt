@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package pimonitor.client

import bitframe.client.BitframeService
import pimonitor.authentication.signup.SignUpService
import pimonitor.client.evaluation.businesses.BusinessesService
import pimonitor.client.monitors.MonitorsService
import kotlin.js.JsExport

interface PiMonitorService : BitframeService {
    val signUp: SignUpService
    val monitors: MonitorsService
    val businesses: BusinessesService
}