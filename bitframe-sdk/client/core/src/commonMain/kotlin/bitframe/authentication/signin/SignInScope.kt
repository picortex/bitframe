@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin

import bitframe.api.BitframeService
import bitframe.actors.spaces.Space
import bitframe.client.BitframeScopeConfig
import bitframe.client.UIScope
import viewmodel.ViewModel
import kotlin.js.JsExport
import bitframe.authentication.signin.SignInIntent as Intent
import bitframe.authentication.signin.SignInState as State

open class SignInScope(config: BitframeScopeConfig) : UIScope<Intent, State> {

    override val service: BitframeService = config.service

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