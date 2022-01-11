@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.authentication.signin.SignInViewModel
import bitframe.authentication.spaces.Space
import bitframe.client.BitframeViewModelConfig
import bitframe.client.UIScope
import viewmodel.ViewModel
import bitframe.authentication.signin.SignInIntent as Intent
import bitframe.authentication.signin.SignInState as State

open class SignInScope(config: BitframeViewModelConfig) : UIScope<Intent, State> {

    override val viewModel: ViewModel<Intent, State> by lazy {
        SignInViewModel(config)
    }

    val initForm = {
        viewModel.post(Intent.InitForm)
    }

    val submit = { cred: SignInCredentials ->
        viewModel.post(Intent.Submit(cred.toSignInCredentials()))
    }

    val resolve = { space: Space ->
        viewModel.post(Intent.Resolve(space))
    }
}