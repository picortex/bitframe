@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.authentication.signin.*
import bitframe.authentication.spaces.Space
import useEventHandler
import viewmodel.ViewModel

class SignInScope(service: SignInService) {

    val viewModel: ViewModel<SignInIntent, SignInState> by lazy { SignInViewModel(service) }

    val submit = { cred: SignInCredentials ->
        viewModel.post(SignInIntent.Submit(cred.toSignInCredentials()))
    }

    val resolve = { space: Space ->
        viewModel.post(SignInIntent.Resolve(space))
    }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(service.bus, SignInService.SIGN_IN_EVENT_ID, it)
    }
}