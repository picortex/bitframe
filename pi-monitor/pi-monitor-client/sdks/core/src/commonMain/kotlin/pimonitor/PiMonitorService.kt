package pimonitor

import bitframe.BitframeService
import bitframe.authentication.ClientConfiguration
import bitframe.authentication.SignInService
import pimonitor.authentication.SignUpService
import platform.ExecutionEnvironment
import kotlin.js.JsExport

@JsExport
abstract class PiMonitorService : BitframeService {
    abstract override val config: ClientConfiguration
    abstract override val platform: ExecutionEnvironment
    abstract val signUp: SignUpService
    abstract override val signIn: SignInService
}