@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import bitframe.client.signin.SignInService
import bitframe.core.Session
import pimonitor.client.register.RegisterService
import pimonitor.core.signup.SignUpResult
import pimonitor.core.signup.SignUpService
import useEventHandler
import useViewModelState
import pimonitor.client.signup.SignUpIntent as Intent
import pimonitor.client.signup.SignUpState as State

class SignUpReactScope internal constructor(
    override val config: UIScopeConfig<RegisterService>
) : SignUpScope(config), ReactUIScope<Intent, State> {

    override val useScopeState: () -> State = {
        useViewModelState(viewModel)
    }

    private val bus get() = config.service.signin.config.bus
    
    val useSignUpEvent: (callback: (SignUpResult) -> Unit) -> Unit = {
        useEventHandler(bus, SignUpService.SIGN_UP_EVENT_TOPIC, it)
    }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(bus, SignInService.SIGN_IN_EVENT_TOPIC, it)
    }
}