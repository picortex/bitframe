@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import bitframe.authentication.signup.SignUpService
import bitframe.client.evaluation.businesses.BusinessesService
import bitframe.client.monitors.MonitorsService
import kotlin.js.JsExport

interface PiMonitorService : BitframeService {
    val signUp: SignUpService
    val monitors: MonitorsService
    val businesses: BusinessesService
}