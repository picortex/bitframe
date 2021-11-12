@file:JsExport

package pimonitor.client

import bitframe.client.BitframeService
import bitframe.client.BitframeServiceConfig
import pimonitor.authentication.signup.SignUpService
import pimonitor.client.evaluation.businesses.BusinessesService
import pimonitor.client.monitors.MonitorsService
import kotlin.js.JsExport

abstract class PiMonitorService : BitframeService {
    abstract val signUp: SignUpService
    abstract val monitors: MonitorsService
    abstract val businesses: BusinessesService
}