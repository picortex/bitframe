package bitframe.client.signin

import bitframe.client.MicroScope
import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import bitframe.core.Space
import bitframe.core.signin.SignInRawParams
import viewmodel.ViewModel
import kotlin.js.JsExport
import bitframe.client.signin.SignInState as State
import bitframe.client.signin.SignInIntent as Intent

fun SignInScope(
    config: UIScopeConfig<SignInService>
) = MicroScope {
    viewModel(SignInViewModel(config))
    intents(SignInIntents(viewModel))
}