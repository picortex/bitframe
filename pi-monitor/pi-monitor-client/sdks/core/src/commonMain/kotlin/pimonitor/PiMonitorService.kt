@file:JsExport

package pimonitor

import bitframe.BitframeService
import bitframe.authentication.signin.SignInService
import pimonitor.authentication.signup.SignUpService
import pimonitor.evaulation.business.BusinessService
import kotlin.js.JsExport

abstract class PiMonitorService : BitframeService {
    abstract override val signIn: SignInService
    abstract val signUp: SignUpService
    abstract val businesses: BusinessService
}