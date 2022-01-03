@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup.exports

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.Session
import bitframe.client.ReactUIScope
import pimonitor.api.PiMonitorService
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService
import pimonitor.authentication.signup.SignUpViewModel
import useEventHandler
import useViewModelState
import viewmodel.ViewModel
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

class SignUpReactScope(
    private val service: PiMonitorService
) : SignUpScope(service), ReactUIScope<Intent, State> {

    val useSignUpEvent: (callback: (SignUpResult) -> Unit) -> Unit = {
        useEventHandler(service.signIn.config.bus, SignUpService.SIGN_UP_EVENT_ID, it)
    }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(service.signIn.config.bus, SignInService.SIGN_IN_EVENT_ID, it)
    }
}