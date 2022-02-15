package pimonitor.client.register

import bitframe.client.signin.SignInService
import pimonitor.client.signup.SignUpService
import kotlin.js.JsExport

@JsExport
class RegisterService(
    val signup: SignUpService,
    val signin: SignInService
)