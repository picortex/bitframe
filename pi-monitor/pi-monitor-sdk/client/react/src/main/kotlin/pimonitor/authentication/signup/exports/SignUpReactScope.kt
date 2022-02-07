@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup.exports

import bitframe.authentication.client.signin.SignInService
import bitframe.client.ReactUIScope
import bitframe.service.client.Session
import pimonitor.PiMonitorViewModelConfig
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService
import useEventHandler
import useViewModelState
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

class SignUpReactScope internal constructor(
    config: PiMonitorViewModelConfig
) : SignUpScope(config), ReactUIScope<Intent, State> {

    override val useStateFromViewModel: () -> State = {
        useViewModelState(viewModel)
    }

    val useSignUpEvent: (callback: (SignUpResult) -> Unit) -> Unit = {
        useEventHandler(config.bus, SignUpService.SIGN_UP_EVENT_TOPIC, it)
    }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(config.bus, SignInService.SIGN_IN_EVENT_TOPIC, it)
    }
}