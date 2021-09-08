package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.SignInService
import platform.Platform
import kotlin.js.JsExport

@JsExport
abstract class BitframeService {
    val platform: Platform get() = Platform
    abstract val config: ClientConfiguration
    abstract val authentication: SignInService
}