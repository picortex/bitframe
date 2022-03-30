@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.signin

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import bitframe.core.Space
import bitframe.core.signin.RawSignInCredentials
import viewmodel.ViewModel
import kotlin.js.JsExport
import bitframe.client.signin.SignInState as State
import bitframe.client.signin.SignInIntent as Intent

open class SignInScope(
    override val config: UIScopeConfig<SignInService>
) : UIScope<State> {

    override val viewModel: ViewModel<Intent, State> by lazy {
        SignInViewModel(config)
    }

    val initForm = {
        viewModel.post(Intent.InitForm)
    }

    val submit = { cred: RawSignInCredentials ->
        viewModel.post(Intent.Submit(cred))
    }

    val resolveConundrum = { space: Space ->
        viewModel.post(Intent.ResolveConundrum(space))
    }
}