@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.Session
import bitframe.client.BitframeViewModelConfig
import bitframe.client.ReactUIScope
import useEventHandler
import useViewModelState
import bitframe.authentication.signin.SignInIntent as Intent
import bitframe.authentication.signin.SignInState as State

class SignInReactScope(config: BitframeViewModelConfig) : SignInScope(config), ReactUIScope<Intent, State> {
    override val useStateFromViewModel: () -> State = { useViewModelState(viewModel) }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(config.service.config.bus, SignInService.SIGN_IN_EVENT_TOPIC, it)
    }
}