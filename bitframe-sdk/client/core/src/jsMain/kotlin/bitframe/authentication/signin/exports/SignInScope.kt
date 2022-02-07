@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.api.BitframeService
import bitframe.authentication.signin.SignInViewModel
import bitframe.actors.spaces.Space
import bitframe.client.BitframeViewModelConfig
import bitframe.client.UIScope
import viewmodel.ViewModel
import bitframe.authentication.signin.SignInIntent as Intent
import bitframe.authentication.signin.SignInState as State

open class SignInScope(config: BitframeViewModelConfig) : UIScope<Intent, State> {

    override val service: BitframeService = config.service

    override val viewModel: ViewModel<Intent, State> by lazy {
        SignInViewModel(config)
    }

    val initForm = {
        viewModel.post(Intent.InitForm)
    }

    val submit = { cred: SignInCredentials ->
        viewModel.post(Intent.Submit(cred.toSignInCredentials()))
    }

    val resolveConundrum = { space: Space ->
        viewModel.post(Intent.ResolveConundrum(space))
    }
}