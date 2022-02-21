@file:JsExport

package bitframe.client.signin

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import bitframe.core.Session
import useEventHandler
import useViewModelState
import bitframe.client.signin.SignInIntent as Intent
import bitframe.client.signin.SignInState as State

class SignInReactScope(config: UIScopeConfig<SignInService>) : SignInScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = { useViewModelState(viewModel) }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(config.service.config.bus, SignInService.SIGN_IN_EVENT_TOPIC, it)
    }
}