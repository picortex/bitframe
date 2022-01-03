@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.api.BitframeService
import bitframe.authentication.signin.*
import bitframe.authentication.spaces.Space
import bitframe.authentication.client.signin.SignInService
import bitframe.client.UIScope
import viewmodel.ViewModel
import bitframe.authentication.signin.SignInIntent as Intent
import bitframe.authentication.signin.SignInState as State

open class SignInScope(service: BitframeService) : UIScope<Intent, State> {

    override val viewModel: ViewModel<Intent, State> by lazy {
        SignInViewModel(service.signIn, service.signIn.config.cache)
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