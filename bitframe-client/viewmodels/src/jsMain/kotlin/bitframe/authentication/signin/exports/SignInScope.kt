@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.BitframeService
import bitframe.authentication.signin.*
import bitframe.authentication.spaces.Space
import useEventHandler
import viewmodel.ViewModel
import bitframe.authentication.signin.SignInIntent as Intent
import bitframe.authentication.signin.SignInState as State

class SignInScope(service: BitframeService) {

    val viewModel: ViewModel<Intent, State> by lazy {
        SignInViewModel(service.signIn, service.cache)
    }

    val submit = { cred: SignInCredentials ->
        viewModel.post(Intent.Submit(cred.toSignInCredentials()))
    }

    val resolve = { space: Space ->
        viewModel.post(Intent.Resolve(space))
    }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(service.signIn.bus, SignInService.SIGN_IN_EVENT_ID, it)
    }
}