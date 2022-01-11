@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup.exports

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.Session
import bitframe.client.ReactUIScope
import pimonitor.PiMonitorViewModelConfig
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService
import useEventHandler
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

class SignUpReactScope(
    config: PiMonitorViewModelConfig
) : SignUpScope(config), ReactUIScope<Intent, State> {

    val useSignUpEvent: (callback: (SignUpResult) -> Unit) -> Unit = {
        useEventHandler(config.service.bus, SignUpService.SIGN_UP_EVENT_ID, it)
    }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(config.service.bus, SignInService.SIGN_IN_EVENT_ID, it)
    }
}