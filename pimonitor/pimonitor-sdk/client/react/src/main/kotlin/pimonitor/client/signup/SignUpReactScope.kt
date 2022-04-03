@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import bitframe.client.signin.SignInService
import bitframe.core.Session
import pimonitor.client.PiMonitorApi
import pimonitor.core.signup.SignUpResult
import useEventHandler
import viewmodel.asState
import pimonitor.client.signup.SignUpState as State

class SignUpReactScope internal constructor(
    override val config: UIScopeConfig<PiMonitorApi>
) : SignUpScope(config), ReactUIScope<State> {

    override val useScopeState = { viewModel.asState() }

    private val bus get() = config.service.config.bus

    val useSignUpEvent: (callback: (SignUpResult) -> Unit) -> Unit = {
        useEventHandler(bus, SignUpEvent.TOPIC, it)
    }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(bus, SignInService.SIGN_IN_EVENT_TOPIC, it)
    }
}