@file:JsExport

package pimonitor

import bitframe.BitframeService
import bitframe.authentication.ClientConfiguration
import bitframe.authentication.signin.SignInService
import pimonitor.authentication.signup.SignUpService
import pimonitor.evaulation.business.BusinessService
import platform.ExecutionEnvironment
import kotlin.js.JsExport

abstract class PiMonitorService : BitframeService {
    abstract override val config: ClientConfiguration
    abstract override val platform: ExecutionEnvironment
    abstract override val signIn: SignInService
    abstract val signUp: SignUpService
    abstract val business: BusinessService
}