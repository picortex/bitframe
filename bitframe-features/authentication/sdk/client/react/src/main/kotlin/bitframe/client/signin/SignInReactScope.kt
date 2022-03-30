@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.signin

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import bitframe.core.Session
import useEventHandler
import useViewModelState
import viewmodel.asState
import bitframe.client.signin.SignInIntent as Intent
import bitframe.client.signin.SignInState as State

class SignInReactScope(
    override val config: UIScopeConfig<SignInService>
) : SignInScope(config), ReactUIScope<State> {
    override val useScopeState = { viewModel.asState() }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(config.service.config.bus, SignInService.SIGN_IN_EVENT_TOPIC, it)
    }
}